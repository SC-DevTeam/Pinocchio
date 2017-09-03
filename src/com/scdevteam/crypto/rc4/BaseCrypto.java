package com.scdevteam.crypto.rc4;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public abstract class BaseCrypto {
    byte[] ClientKey;
    byte[] ServerKey;

    byte[] encrypt(byte[] message) {
        return null;
    }

    byte[] decrypt(byte[] message) {
        return null;
    }

    public abstract void decryptPacket(ResponseMessage message);
    public abstract void encryptPacket(RequestMessage message);
}
