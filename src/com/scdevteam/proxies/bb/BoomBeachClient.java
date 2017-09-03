package com.scdevteam.proxies.bb;

import com.scdevteam.proto.HostsMap;
import com.scdevteam.proxies.BaseClient;

public class BoomBeachClient extends BaseClient {
    public BoomBeachClient(BoomBeachProxy proxy) {
        super(proxy);
    }

    @Override
    public String getKey() {
        return "f6e07c02b728dfee9a849204b2ac1a3004ae3ce191092ae9ae5188db41768528";
    }

    @Override
    public String getGameHost() {
        return HostsMap.PROD_BOOM_BEACH;
    }

    @Override
    public String getStageGameHost() { return HostsMap.STAGE_BOOM_BEACH; }
}
