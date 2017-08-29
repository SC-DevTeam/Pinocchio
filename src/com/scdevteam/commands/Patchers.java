package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Patchers extends BaseCommand {

    private int mGame;

    /**
     * @param game 0: CR
     *             1: COC
     *             2: BB
     *             3: HH
     *             4: BS
     */
    public Patchers(int game) {
        mGame = game;
    }

    public Patchers(String game) {
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

        initPatch();
    }

    private void initPatch() {
        String result = execShellCmd("adb");
        if (result.startsWith("Android Debug Bridge")) {
            WriterUtils.postSuccess("ADB found. Checking paths...");
        } else {
            WriterUtils.postError("ADB is not installed on current system env.");
        }
    }
}
