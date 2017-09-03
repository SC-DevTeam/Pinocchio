package com.scdevteam.proxies.cr;

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
        return "game.clashroyaleapp.com";
    }

    @Override
    public String getStageGameHost() { return "stage.scroll.supercelltest.com"; }
}
