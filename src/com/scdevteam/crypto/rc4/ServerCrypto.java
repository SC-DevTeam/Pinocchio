package com.scdevteam.crypto.rc4;

import com.scdevteam.messages.RequestMessage;
import com.scdevteam.messages.ResponseMessage;
import com.scdevteam.proxies.BaseProxy;

public class ServerCrypto extends BaseCrypto {
    private final BaseProxy mProxy;

    public ServerCrypto(BaseProxy proxy, String cryptoKey, String Nonce) {
        mProxy = proxy;

        if (Nonce != null)
        {
            cryptoKey = cryptoKey + Nonce;
        }
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
