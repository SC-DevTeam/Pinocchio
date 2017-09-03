package com.scdevteam.crypto.rc4;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public class ClientCrypto extends BaseCrypto {

    private final ServerCrypto mServer;

    public ClientCrypto(byte[] serverKey, ServerCrypto serverRc4) {
        super();

        this.ServerKey = serverKey;
        this.mServer = serverRc4;
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
        // Decrypt.
    }

    @Override
    public void encryptPacket(RequestMessage message) {
        // Encrypt.
    }
}
