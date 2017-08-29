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
            WriterUtils.postSuccess("ADB ok...");
            result = execShellCmd("dd");
            if (result.startsWith("dd (coreutils)")) {
                WriterUtils.postSuccess("CoreUtils ok...");
                WriterUtils.post("");
                WriterUtils.postInfo("Checking paths...");
                switch (mGame) {
                    case 0:
                        patchCr();
                        break;
                }
            } else {
                WriterUtils.postError("CoreUtils are not installed on current system env.");
            }
        } else {
            WriterUtils.postError("ADB is not installed on current system env.");
        }
    }

    private void patchCr() {
        String result = execShellCmd("adb shell su -c cp /data/data/com.supercell.clashroyale/lib/libg.so /sdcard/");
        if (result.isEmpty()) {
            result = execShellCmd("adb pull /sdcard/libg.so");
        } else {
            WriterUtils.postError("Looks like Clash Royale is not installed on your phone or not accessible.");
        }
    }
}
