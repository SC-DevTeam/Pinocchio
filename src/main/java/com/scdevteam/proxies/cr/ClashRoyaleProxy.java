package com.scdevteam.proxies.cr;

import com.scdevteam.messages.cr.CRMapper;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proxies.BaseClient;
import com.scdevteam.proxies.BaseProxy;

public class ClashRoyaleProxy extends BaseProxy {

    @Override
    public BaseClient buildClient() {
        return new ClashRoyaleClient(this);
    }

    @Override
    public GameMapper buildMapper() {
        return new CRMapper();
    }

    @Override
    public String getMagicKey() {
        return null;
    }
}
