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
        return "56b577a8996f52c388981942811c6e9b8d39dc3e281f1ce0c047a3d8e55cb222";
    }
}