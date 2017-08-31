package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

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

        switch (mGame) {
            case 0:

                break;
        }
    }
}
