package com.scdevteam.proxies.cr;

import com.scdevteam.proto.HostsMap;
import com.scdevteam.proxies.BaseClient;

public class ClashRoyaleClient extends BaseClient {

    public ClashRoyaleClient(ClashRoyaleProxy proxy) {
        super(proxy);
    }

    @Override
    public String getKey() {
        return "ac30dcbea27e213407519bc05be8e9d930e63f873858479946c144895fa3a26b";
    }

    @Override
    public String getGameHost() {
        return HostsMap.PROD_CLASH_ROYALE;
    }

    @Override
    public String getStageGameHost() { return HostsMap.STAGE_CLASH_ROYALE; }
}
