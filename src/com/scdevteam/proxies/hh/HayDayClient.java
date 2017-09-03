package com.scdevteam.proxies.hh;

import com.scdevteam.proxies.BaseClient;

public class HayDayClient extends BaseClient {

    public HayDayClient(HayDayProxy proxy) {
        super(proxy);
    }

    @Override
    public String getKey() {
        return "beb655127406245639b2ac70f026089531676c107f11a0648958ed16f5fff410";
    }

    @Override
    public String getGameHost() {
        return "game.haydaygame.com";
    }
}
