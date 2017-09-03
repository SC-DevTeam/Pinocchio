package com.scdevteam.proxies.bb;

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
        return "game.boombeachgame.com";
    }

    @Override
    public String getStageGameHost() { return "stage.reef.supercelltest.com"; }
}
