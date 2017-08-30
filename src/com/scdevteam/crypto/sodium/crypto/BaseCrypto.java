package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public abstract class BaseCrypto {
    protected byte[] privateKey = new byte[TweetNaCl.SIGN_SECRET_KEY_BYTES];
    protected byte[] serverKey;
    protected byte[] clientKey = new byte[TweetNaCl.BOX_PUBLIC_KEY_BYTES];
    protected byte[] sharedKey;
    protected Nonce decryptNonce = new Nonce();
    protected Nonce encryptNonce = new Nonce();
    protected byte[] sessionKey;

    public byte[] encrypt(byte[] message) {
        return encrypt(message, null);
    }

    public byte[] encrypt(byte[] message, Nonce nonce) {
        if (nonce == null) {
            encryptNonce.increment();
            nonce = encryptNonce;
        }

        return TweetNaCl.crypto_box(message, nonce.getBytes(), sharedKey);
    }

    public byte[] decrypt(byte[] message) {
        return decrypt(message, null);
    }

    public byte[] decrypt(byte[] message, Nonce nonce) {
        if (nonce == null) {
            decryptNonce.increment();
            nonce = decryptNonce;
        }

        return TweetNaCl.crypto_box_open(message, nonce.getBytes(), sharedKey);
    }

    public abstract void decryptPacket(ResponseMessage message);
    public abstract void encryptPacket(RequestMessage message);
}
