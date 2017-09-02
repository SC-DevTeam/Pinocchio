package com.scdevteam.proxies.cr;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;
import com.scdevteam.crypto.sodium.crypto.ServerCrypto;
import com.scdevteam.messages.cr.CRMapper;
import com.scdevteam.proto.MessageMap;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class ClashRoyaleProxy {

    private OutputStream mOut;
    private ServerCrypto mSodium;

    private ClashRoyaleClient mClient;
    private CRMapper mMapper;

    public void init() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9339);
            WriterUtils.postInfo("Waiting for game to connect");
            Socket clientSocket = serverSocket.accept();

            mOut = clientSocket.getOutputStream();

            WriterUtils.postSuccess("Client connected...");
            WriterUtils.post("");

            mMapper = new CRMapper();
            mSodium = new ServerCrypto(this);
            mClient = new ClashRoyaleClient(this);

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
                    if (map != null) {
                        WriterUtils.post(map);
                    } else {
                        WriterUtils.post(Utils.toHexString(responseMessage.getDecryptedPayload()));
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

    public ClashRoyaleClient getClient() {
        return mClient;
    }

    public CRMapper getMapper() {
        return mMapper;
    }
}
