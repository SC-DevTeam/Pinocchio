package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.Utils;
import com.scdevteam.proto.MessageMap;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ClientCrypto extends BaseCrypto {

    private final ServerCrypto mServerSodium;

    public ClientCrypto(byte[] serverKey, ServerCrypto serverSodium) {
        super();

        mServerSodium = serverSodium;

        TweetNaCl.crypto_box_keypair(clientKey, privateKey, false);
        this.serverKey = serverKey;

        sharedKey = new byte[32];
        TweetNaCl.crypto_box_beforenm(sharedKey, serverKey, privateKey);
        encryptNonce = new Nonce();
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
        if (message.getMessageID() == MessageMap.SERVER_HELLO ||
                (message.getMessageID() == MessageMap.LOGIN_FAILED && sessionKey == null)) {
            int len = Utils.toInt32(Arrays.copyOfRange(message.getEncryptedPayload(), 0, 4));
            sessionKey = Arrays.copyOfRange(message.getEncryptedPayload(),
                    4, 4 + len);
            message.setDecryptedPayload(message.getEncryptedPayload());
        } else if (message.getMessageID() == MessageMap.LOGIN_OK) {
            Nonce nonce = new Nonce(clientKey, serverKey, encryptNonce.getBytes());
            message.setDecryptedPayload(decrypt(message.getEncryptedPayload(), nonce));

            if (message.getDecryptedPayload() != null) {
                decryptNonce = new Nonce(Arrays.copyOfRange(message.getDecryptedPayload(),
                        0, 24));
                mServerSodium.encryptNonce = new Nonce(Arrays.copyOfRange(message.getDecryptedPayload(),
                        0, 24));

                sharedKey = Arrays.copyOfRange(message.getDecryptedPayload(),
                        24, 56);

                message.setDecryptedPayload(Arrays.copyOfRange(message.getDecryptedPayload(),
                        56, message.getDecryptedPayload().length));
            } else {
                message.setDecryptedPayload(decrypt(message.getEncryptedPayload()));
            }
        }
    }

    @Override
    public void encryptPacket(RequestMessage message) {
        switch (message.getMessageID()) {
            case MessageMap.CLIENT_HELLO:
                message.setEncryptedPayload(message.getDecryptedPayload());
                break;
            case MessageMap.LOGIN:
                Nonce nonce = new Nonce(clientKey, serverKey);
                ByteArrayOutputStream toEncrypt = new ByteArrayOutputStream();

                try {
                    toEncrypt.write(sessionKey);
                    toEncrypt.write(encryptNonce.getBytes());
                    toEncrypt.write(message.getDecryptedPayload());
                } catch (IOException ignored) {}

                ByteArrayOutputStream encrypted = new ByteArrayOutputStream();
                try {
                    encrypted.write(clientKey);
                    encrypted.write(encrypt(toEncrypt.toByteArray(), nonce));
                } catch (IOException ignored) {}

                message.setEncryptedPayload(encrypted.toByteArray());
                break;
            default:
                message.setEncryptedPayload(encrypt(message.getDecryptedPayload()));
        }
    }
}
