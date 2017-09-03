package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.Utils;
import com.scdevteam.proto.MessageMap;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.proxies.BaseProxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ServerCrypto extends BaseCrypto {
    private final BaseProxy mProxy;

    public ServerCrypto(BaseProxy proxy, String magicKey) {
        mProxy = proxy;

        if (magicKey != null) {
            this.magicKey = Utils.hexToBuffer(magicKey);
        } else {
            privateKey = Utils.hexToBuffer("1891d401fadb51d25d3a9174d472a9f691a45b974285d47729c45c6538070d85");
            serverKey = Utils.hexToBuffer("72f1a4a4c48e44da0c42310f800e96624e6dc6a641a9d41c3b5039d8dfadc27e");
        }
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
        if (message.getMessageID() == MessageMap.CLIENT_HELLO) {
            if (magicKey != null) {
                serverKey = Utils.hexToBuffer(mProxy.getClient().getKey());
            }

            message.setDecryptedPayload(message.getEncryptedPayload());
        } else if (message.getMessageID() == MessageMap.LOGIN) {
            clientKey = Arrays.copyOf(message.getEncryptedPayload(), 32);

            if (magicKey != null) {
                sharedKey = magicKey;
            } else {
                sharedKey = new byte[32];
                TweetNaCl.crypto_box_beforenm(sharedKey, clientKey, privateKey);
            }

            Nonce nonce = new Nonce(clientKey, serverKey);

            byte[] payload = Arrays.copyOfRange(message.getEncryptedPayload(), 32,
                    message.getEncryptedPayload().length);
            byte[] deciphered = decrypt(payload, nonce);

            sessionKey = Arrays.copyOfRange(deciphered, 0, 24);
            decryptNonce = new Nonce(Arrays.copyOfRange(deciphered, 24, 48));
            mProxy.getClient().getCrypto().encryptNonce = new Nonce(
                    Arrays.copyOfRange(deciphered, 24, 48));

            message.setDecryptedPayload(Arrays.copyOfRange(deciphered, 48, deciphered.length));
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
            Nonce nonce;

            nonce = new Nonce(clientKey, serverKey, decryptNonce.getBytes());

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try {
                bos.write(encryptNonce.getBytes());
                if (magicKey != null) {
                    bos.write(magicKey);
                } else {
                    bos.write(mProxy.getClient().getCrypto().sharedKey);
                }
                bos.write(message.getDecryptedPayload());
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (magicKey != null) {
                sharedKey = magicKey;
            }

            byte[] ciphered = encrypt(bos.toByteArray(), nonce);
            message.setEncryptedPayload(ciphered);

            if (magicKey == null) {
                sharedKey = mProxy.getClient().getCrypto().sharedKey;
            }
        } else {
            message.setEncryptedPayload(encrypt(message.getDecryptedPayload()));
        }
    }
}
