package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public class ServerCrypto extends BaseCrypto {

    public ServerCrypto(byte[] serverKey) {
        super();
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
    }

    @Override
    public void encryptPacket(RequestMessage message) {
    }
}
