package com.scdevteam.proxies;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;
import com.scdevteam.commands.BaseCommand;
import com.scdevteam.crypto.sodium.crypto.BaseCrypto;
import com.scdevteam.crypto.sodium.crypto.ClientCrypto;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.proto.MessageMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class BaseClient implements Runnable {

    private OutputStream mOut;

    private final BaseProxy mProxy;
    private final ClientCrypto mSodium;

    private final Object mLocker = new Object();

    private Socket mGameSocket;
    private BaseCommand.Options mOptions;

    public BaseClient(BaseProxy proxy) {
        mProxy = proxy;
        mSodium = new ClientCrypto(Utils.hexToBuffer(getKey()), mProxy.getCrypto());
        new Thread(this).start();
    }

    void setOptions(BaseCommand.Options options) {
        mOptions = options;
    }

    @Override
    public void run() {
        try {
            mGameSocket = new Socket();
            mGameSocket.connect(new InetSocketAddress(getGameHost(), 9339));

            InputStream inputStream = mGameSocket.getInputStream();
            mOut = mGameSocket.getOutputStream();

            synchronized (mLocker) {
                mLocker.notifyAll();
            }

            while (mGameSocket.isConnected()) {
                byte[] headers = new byte[7];

                if (inputStream.read(headers, 0, 7) > 0) {
                    int msgId = Utils.toInt16(Arrays.copyOfRange(headers, 0, 2));
                    int len = Utils.toInt24(Arrays.copyOfRange(headers, 2, 5));
                    int ver = Utils.toInt16(Arrays.copyOfRange(headers, 5, 7));

                    ResponseMessage responseMessage = new ResponseMessage(msgId, len, ver);

                    ByteBuffer payload = ByteBuffer.allocate(len);

                    int o = len;
                    while (o != 0) {
                        byte[] a = new byte[o];

                        int r;
                        if ((r = inputStream.read(a, 0, o)) == -1) {
                            break;
                        }
                        o -= r;

                        payload.put(ByteBuffer.wrap(a, 0, r));
                    }

                    responseMessage.finish(payload, mSodium);

                    WriterUtils.postDanger("[SERVER] " +
                            MessageMap.getMessageType(responseMessage.getMessageID()) +
                            " (" + responseMessage.getMessageID() + ")");

                    String map = MessageMap.getMap(mProxy.getMapper(),
                            responseMessage.getMessageID(),
                            responseMessage.getDecryptedPayload());

                    if (mOptions.haveOption("hex")) {
                        WriterUtils.post(Utils.hexDump(responseMessage.getDecryptedPayload()));
                    }

                    if (map != null) {
                        WriterUtils.post(map);
                    }
                    WriterUtils.post("");

                    mProxy.sendMessageToClient(responseMessage.getMessageID(),
                            responseMessage.getVersion(), responseMessage.getDecryptedPayload());
                }
            }

            mProxy.dispose();
            WriterUtils.postError("Game server disconnected");
        } catch (IOException ignored) {
            mProxy.dispose();
            WriterUtils.postError("Game server disconnected");
        }
    }

    public void sendMessageToServer(final int messageId, final int version, final byte[] payload) {
        final RequestMessage requestMessage =
                new RequestMessage(messageId, payload.length, version, payload, mSodium);

        synchronized (mLocker) {
            while (mOut == null) {
                try {
                    mLocker.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        try {
            mOut.write(requestMessage.buildMessage().array());
            mOut.flush();
        } catch (IOException ignored) {
            mProxy.dispose();
        }
    }

    void dispose() {
        try {
            mGameSocket.close();
        } catch (Exception ignored) {
        }
    }

    public ClientCrypto getCrypto() {
        return mSodium;
    }

    public abstract String getKey();

    public abstract String getGameHost();

    public abstract String getStageGameHost();
}
