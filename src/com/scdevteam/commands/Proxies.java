package com.scdevteam.commands;

import com.scdevteam.WriterUtils;
import com.scdevteam.proxies.BaseProxy;
import com.scdevteam.proxies.bb.BoomBeachProxy;
import com.scdevteam.proxies.coc.ClashOfClansProxy;
import com.scdevteam.proxies.cr.ClashRoyaleProxy;
import com.scdevteam.proxies.hh.HayDayProxy;

public class Proxies extends BaseCommand {
    private final int mGame;

    public Proxies(String game) {
        switch (game) {
            case "cr":
                mGame = 0;
                break;
            case "coc":
                mGame = 1;
                break;
            case "bb":
                mGame = 2;
                break;
            case "hh":
                mGame = 3;
                break;
            case "bs":
                mGame = 4;
                break;
            default:
                mGame = -1;
                break;
        }
    }

    @Override
    public void execute() {
        if (mGame == -1) {
            WriterUtils.postError("Game not valid. Use [cr - coc - bb - hh - bs]");
            return;
        }

        // Persistent
        while (true) {
            BaseProxy baseProxy;
            switch (mGame) {
                case 0:
                    baseProxy = new ClashRoyaleProxy();
                    break;
                case 1:
                    baseProxy = new ClashOfClansProxy();
                    break;
                case 2:
                    baseProxy = new BoomBeachProxy();
                    break;
                case 3:
                    baseProxy = new HayDayProxy();
                    break;
                default:
                    WriterUtils.postError("This game is currently not supported by Pinocchio.");
                    return;
            }

            baseProxy.init(getOptions());
        }
    }
}
