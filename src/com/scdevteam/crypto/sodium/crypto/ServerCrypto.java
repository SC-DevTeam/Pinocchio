package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.Utils;
import com.scdevteam.maps.MessageMap;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.proxies.cr.ClashRoyaleProxy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ServerCrypto extends BaseCrypto {
    private final ClashRoyaleProxy mProxy;

    public ServerCrypto(ClashRoyaleProxy proxy) {
        mProxy = proxy;

        privateKey = Utils.hexToBuffer("1891d401fadb51d25d3a9174d472a9f691a45b974285d47729c45c6538070d85");
        serverKey = Utils.hexToBuffer("72f1a4a4c48e44da0c42310f800e96624e6dc6a641a9d41c3b5039d8dfadc27e");
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
        if (message.getMessageID() == MessageMap.CLIENT_HELLO) {
            message.setDecryptedPayload(message.getEncryptedPayload());
        } else if (message.getMessageID() == MessageMap.LOGIN) {
            clientKey = Arrays.copyOf(message.getEncryptedPayload(), 32);

            sharedKey = new byte[32];
            TweetNaCl.crypto_box_beforenm(sharedKey, clientKey, privateKey);

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
            Nonce nonce = new Nonce(clientKey, serverKey, decryptNonce.getBytes());

            ByteArrayOutputStream toEncrypt = new ByteArrayOutputStream();
            try {
                toEncrypt.write(encryptNonce.getBytes());
                toEncrypt.write(sharedKey);
                toEncrypt.write(message.getDecryptedPayload());
            } catch (IOException e) {
                e.printStackTrace();
            }
            message.setEncryptedPayload(encrypt(toEncrypt.toByteArray(), nonce));

            sharedKey = mProxy.getClient().getCrypto().sharedKey;
        } else {
            message.setEncryptedPayload(encrypt(message.getDecryptedPayload()));
        }
    }
}
