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

    @Override
    public String getMagicKey() {
        return "c33b60402d08954268628efd39de9a6869b94d4f91efd0da71f6be2537f870b4";
    }
}