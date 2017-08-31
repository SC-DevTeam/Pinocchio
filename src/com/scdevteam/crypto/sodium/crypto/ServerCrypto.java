package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.Utils;
import com.scdevteam.maps.MessageMap;
import com.scdevteam.messages.Message;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

import java.nio.ByteBuffer;

public class ServerCrypto extends BaseCrypto {

    public ServerCrypto() {
        this.privateKey = Utils.hexToBuffer("1891d401fadb51d25d3a9174d472a9f691a45b974285d47729c45c6538070d85");
        this.serverKey = Utils.hexToBuffer("72f1a4a4c48e44da0c42310f800e96624e6dc6a641a9d41c3b5039d8dfadc27e");
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
        if (message.getMessageID() == MessageMap.CLIENT_HELLO) {
            message.setDecryptedPayload(message.getEncryptedPayload());
        } else if (message.getMessageID() == MessageMap.LOGIN) {
            ByteBuffer payloadBuffer = ByteBuffer.wrap(message.getEncryptedPayload());

            this.clientKey = new byte[32];
            payloadBuffer.get(this.clientKey, 0, 32);

            Nonce nonce = new Nonce(clientKey, serverKey);

            ByteBuffer decrypted = ByteBuffer.wrap(decrypt(payloadBuffer.array(), nonce));

            if (message.getDecryptedPayload() != null) {
                this.sessionKey = new byte[24];
                decrypted.get(sessionKey, 0, 24);
                byte[] decNonce = new byte[24];
                decrypted.get(decNonce, 0, 24);
                decryptNonce = new Nonce(decNonce);
                message.setDecryptedPayload(decrypted.array());
            }
        } else {
            message.setDecryptedPayload(decrypt(message.getEncryptedPayload()));
        }
    }

    @Override
    public void encryptPacket(RequestMessage message) {
        if (message.getMessageID() == MessageMap.SERVER_HELLO ||
                message.getMessageID() == MessageMap.LOGIN_FAILED) {
            message.setEncryptedPayload(message.getDecryptedPayload());
        } else if (message.getMessageID() == MessageMap.LOGIN_OK) {
            Nonce nonce = new Nonce(clientKey, serverKey, decryptNonce.getBytes());
            ByteBuffer byteBuffer = ByteBuffer.allocate(encryptNonce.getBytes().length +
                    sharedKey.length + message.getDecryptedPayload().length);
            byteBuffer.put(encryptNonce.getBytes());
            byteBuffer.put(sharedKey);
            byteBuffer.put(message.getDecryptedPayload());
            message.setEncryptedPayload(encrypt(byteBuffer.array(), nonce));
        } else {
            message.setEncryptedPayload(encrypt(message.getEncryptedPayload()));
        }
    }
}
