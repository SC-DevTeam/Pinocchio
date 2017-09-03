package com.scdevteam.proxies.hh;

import com.scdevteam.messages.hh.HHMapper;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proxies.BaseClient;
import com.scdevteam.proxies.BaseProxy;

public class HayDayProxy extends BaseProxy {

    @Override
    public BaseClient buildClient() {
        return new HayDayClient(this);
    }

    @Override
    public GameMapper buildMapper() {
        return new HHMapper();
    }

    @Override
    public String getMagicKey() {
        return "0a24f0048b79a85e86e6c345d6f469a65e1355c78d4c241f33841b3e52418e13";
    }
}
