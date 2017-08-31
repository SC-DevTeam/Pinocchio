package com.scdevteam.proxies.cr;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;
import com.scdevteam.crypto.sodium.crypto.BaseCrypto;
import com.scdevteam.crypto.sodium.crypto.ClientCrypto;
import com.scdevteam.maps.MessageMap;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.utils.cr.RequestBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class ClashRoyaleClient implements Runnable {

    private OutputStream mOut;

    private final ClashRoyaleProxy mProxy;
    private final ClientCrypto mSodium;

    public ClashRoyaleClient(ClashRoyaleProxy proxy) {
        mProxy = proxy;
        mSodium = new ClientCrypto(Utils.hexToBuffer("ac30dcbea27e213407519bc05be8e9d930e63f873858479946c144895fa3a26b"));
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress("game.clashroyaleapp.com", 9339));

            InputStream inputStream = socket.getInputStream();
            mOut = socket.getOutputStream();

            while (socket.isConnected()) {
                byte[] headers = new byte[7];

                if (inputStream.read(headers, 0, 7) > 0) {
                    int msgId = Utils.toInt16(Arrays.copyOfRange(headers, 0, 2));
                    int len = Utils.toInt24(Arrays.copyOfRange(headers, 2, 5));
                    int ver = Utils.toInt16(Arrays.copyOfRange(headers, 5, 7));

                    ResponseMessage responseMessage = new ResponseMessage(msgId, len, ver);

                    StringBuilder builder = new StringBuilder();
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

                    String map = MessageMap.getMap(responseMessage.getMessageID(),
                            responseMessage.getDecryptedPayload());
                    builder.append("IN ---> ")
                            .append(MessageMap.getMessageType(responseMessage.getMessageID()))
                            .append("\nLENGTH: ")
                            .append(responseMessage.getPayloadLength());
                    if (map != null) {
                        builder.append("\n\nMAP:\n")
                                .append(map);
                    } else {
                        builder.append("\n\nPAYLOAD:\n")
                                .append(Utils.toHexString(responseMessage.getDecryptedPayload()));
                    }

                    if (builder.length() > 0) {
                        String d = builder.toString();
                        WriterUtils.postInfo(d);
                    }

                    mProxy.sendMessageToClient(responseMessage.getMessageID(),
                            responseMessage.getVersion(), responseMessage.getDecryptedPayload());
                }
            }

            WriterUtils.postError("Game server disconnected");
        } catch (IOException ignored) {
            WriterUtils.postError("Game server disconnected");
        }
    }

    public void sendMessageToServer(final int messageId, final int version, final byte[] payload) {
        try {
            final RequestMessage requestMessage =
                    new RequestMessage(messageId, payload.length, version, payload, mProxy.getCrypto());

            mOut.write(requestMessage.buildMessage().array());
            mOut.flush();
            WriterUtils.postInfo("OUT ---> " +
                    MessageMap.getMessageType(messageId) +
                    "\nLENGTH: " + payload.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientCrypto getCrypto() {
        return mSodium;
    }
}
