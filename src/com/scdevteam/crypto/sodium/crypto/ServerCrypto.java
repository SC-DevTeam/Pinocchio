package com.scdevteam.crypto.sodium.crypto;

import com.scdevteam.Utils;
import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;

public class ServerCrypto extends BaseCrypto {

    public ServerCrypto(byte[] serverKey) {
        this.privateKey = Utils.hexToBuffer("1891d401fadb51d25d3a9174d472a9f691a45b974285d47729c45c6538070d85");
        this.serverKey = Utils.hexToBuffer("72f1a4a4c48e44da0c42310f800e96624e6dc6a641a9d41c3b5039d8dfadc27e");
    }

    @Override
    public void decryptPacket(ResponseMessage message) {
    }

    @Override
    public void encryptPacket(RequestMessage message) {
    }
}
