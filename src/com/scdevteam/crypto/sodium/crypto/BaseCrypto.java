package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public abstract class BaseCrypto {
    byte[] privateKey = new byte[TweetNaCl.SIGN_SECRET_KEY_BYTES];
    byte[] serverKey;
    byte[] clientKey = new byte[TweetNaCl.BOX_PUBLIC_KEY_BYTES];
    byte[] sharedKey;
    Nonce decryptNonce;
    Nonce encryptNonce;
    byte[] sessionKey;

    // For new encryption
    byte[] realPublicServerKey;
    byte[] magicKey;

    byte[] encrypt(byte[] message) {
        return encrypt(message, null);
    }

    byte[] encrypt(byte[] message, Nonce nonce) {
        if (nonce == null) {
            encryptNonce.increment();
            nonce = encryptNonce;
        }

        return TweetNaCl.crypto_box(message, nonce.getBytes(), sharedKey);
    }

    byte[] decrypt(byte[] message) {
        return decrypt(message, null);
    }

    byte[] decrypt(byte[] message, Nonce nonce) {
        if (nonce == null) {
            decryptNonce.increment();
            nonce = decryptNonce;
        }

        return TweetNaCl.crypto_box_open(message, nonce.getBytes(), sharedKey);
    }

    public abstract void decryptPacket(ResponseMessage message);
    public abstract void encryptPacket(RequestMessage message);
}
