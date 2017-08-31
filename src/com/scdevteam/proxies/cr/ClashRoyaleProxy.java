package com.scdevteam.proxies.cr;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;
import com.scdevteam.crypto.sodium.crypto.BaseCrypto;
import com.scdevteam.crypto.sodium.crypto.ClientCrypto;
import com.scdevteam.crypto.sodium.crypto.ServerCrypto;
import com.scdevteam.maps.MessageMap;
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

    public ClashRoyaleProxy() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(9339);
            WriterUtils.postInfo("Waiting for game to connect");
            Socket clientSocket = serverSocket.accept();

            mOut = clientSocket.getOutputStream();

            WriterUtils.postSuccess("Client connected...");

            mClient = new ClashRoyaleClient(this);
            mSodium = new ServerCrypto(Utils.hexToBuffer(""));

            InputStream inputStream = clientSocket.getInputStream();

            while (clientSocket.isConnected()) {
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
                    new RequestMessage(messageId, payload.length, version, payload, mClient.getCrypto());

            mOut.write(requestMessage.buildMessage().array());
            mOut.flush();
            WriterUtils.postInfo("OUT ---> " +
                    MessageMap.getMessageType(messageId) +
                    "\nLENGTH: " + payload.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerCrypto getCrypto() {
        return mSodium;
    }
}
