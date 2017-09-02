package com.scdevteam.proxies.coc;

import com.scdevteam.messages.coc.COCMapper;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proxies.BaseClient;
import com.scdevteam.proxies.BaseProxy;

public class ClashOfClansProxy extends BaseProxy {

    @Override
    public BaseClient buildClient() {
        return new ClashOfClansClient(this);
    }

    @Override
    public GameMapper buildMapper() {
        return new COCMapper();
    }
}