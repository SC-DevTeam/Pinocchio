package com.scdevteam.proxies.coc;

import com.scdevteam.proto.HostsMap;
import com.scdevteam.proxies.BaseClient;

public class ClashOfClansClient extends BaseClient  {
    public ClashOfClansClient(ClashOfClansProxy proxy) {
        super(proxy);
    }

    @Override
    public String getKey() {
        return "ac69c8fd97bc0b2cd23b7a55e943d7e2c96fc31986fb8f968ae9ca9b1ded1d5e";
    }

    @Override
    public String getGameHost() {
        return HostsMap.PROD_CLASH_OF_CLANS;
    }

    @Override
    public String getStageGameHost() { return HostsMap.STAGE_CLASH_OF_CLANS; }
}
