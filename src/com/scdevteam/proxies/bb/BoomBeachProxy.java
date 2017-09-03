package com.scdevteam.proxies.bb;

import com.scdevteam.messages.bb.BBMapper;
import com.scdevteam.proto.GameMapper;
import com.scdevteam.proxies.BaseClient;
import com.scdevteam.proxies.BaseProxy;

public class BoomBeachProxy extends BaseProxy {
    @Override
    public BaseClient buildClient() {
        return new BoomBeachClient(this);
    }

    @Override
    public GameMapper buildMapper() {
        return new BBMapper();
    }

    @Override
    public String getMagicKey() {
        return "7e6679f308d946981e99db983bbabdf0bd3c9cf2df94b028b32937ecf3197abd";
    }
}
