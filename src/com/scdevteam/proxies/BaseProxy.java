package com.scdevteam.proxies;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;
import com.scdevteam.crypto.sodium.crypto.ServerCrypto;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proto.MessageMap;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public abstract class BaseProxy extends Base {
    private OutputStream mOut;
    private ServerCrypto mSodium;

    private BaseClient mClient;
    private GameMapper mMapper;

    public void init() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9339);
            WriterUtils.postInfo("Waiting for game to connect");
            Socket clientSocket = serverSocket.accept();

            mOut = clientSocket.getOutputStream();

            WriterUtils.postSuccess("Client connected...");
            WriterUtils.post("");

            mMapper = buildMapper();
            mSodium = new ServerCrypto(this, getMagicKey());
            mClient = buildClient();

            InputStream inputStream = clientSocket.getInputStream();

            while (clientSocket.isConnected()) {
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

                    String map = MessageMap.getMap(mMapper, responseMessage.getMessageID(),
                            responseMessage.getDecryptedPayload());

                    WriterUtils.postAwesome("[CLIENT] " +
                            MessageMap.getMessageType(responseMessage.getMessageID()) +
                            " (" + responseMessage.getMessageID() + ")");
                    WriterUtils.post(Utils.toHexString(responseMessage.getDecryptedPayload()));

                    if (map != null) {
                        WriterUtils.post(map);
                    }
                    WriterUtils.post("");

                    mClient.sendMessageToServer(responseMessage.getMessageID(),
                            responseMessage.getVersion(), responseMessage.getDecryptedPayload());
                }
            }

            WriterUtils.postError("Game disconnected.");
        } catch (IOException e) {
            WriterUtils.postError("Game disconnected.");
        }
    }

    public void sendMessageToClient(final int messageId, final int version, final byte[] payload) {
        try {
            final RequestMessage requestMessage =
                    new RequestMessage(messageId, payload.length, version, payload, mSodium);

            mOut.write(requestMessage.buildMessage().array());
            mOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerCrypto getCrypto() {
        return mSodium;
    }

    public GameMapper getMapper() {
        return mMapper;
    }

    public BaseClient getClient() {
        return mClient;
    }

    public abstract BaseClient buildClient();

    public abstract GameMapper buildMapper();

    public abstract String getMagicKey();
}
