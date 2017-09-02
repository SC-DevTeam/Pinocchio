package com.scdevteam.commands;

import com.scdevteam.Utils;
import com.scdevteam.WriterUtils;

public class Patchers extends BaseCommand {

    private final int mGame;
    private final String mHost;
    private final String mKey;

    public Patchers(String game, String host, String key) {
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

        mHost = host;
        mKey = key;
    }

    @Override
    public void execute() {
        if (mGame == -1) {
            WriterUtils.postError("Game not valid. Use [cr - coc - bb - hh - bs] [host] [key optional]");
            return;
        }

        initPatch();
    }

    private void initPatch() {
        String result = execShellCmd("adb");
        if (result.startsWith("Android Debug Bridge")) {
            WriterUtils.postSuccess("ADB ok...");
            result = execShellCmd("dd --v");
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
        if (mHost.length() != 23) {
            WriterUtils.postError("Host must be 23 character length");
            return;
        }

        String result = execShellCmd("adb shell su -c cp /data/data/com.supercell.clashroyale/lib/libg.so /sdcard/");
        if (result.isEmpty()) {
            WriterUtils.postSuccess("Clash Royale found. Pulling libg...");
            execShellCmd("adb pull /sdcard/libg.so");

            result = execShellCmd("dd if=libg.so skip=4412475 bs=1 count=23");
            result = result.split("\n")[0];
            WriterUtils.postSuccess("Current host: " + result);

            WriterUtils.postInfo("Patching with new host: " + mHost);

            boolean success = writePayload(mHost);

            if (success) {
                execShellCmd("dd if=tmp of=libg.so seek=4412475 obs=1 conv=notrunc");
                result = execShellCmd("dd if=libg.so skip=4412475 bs=1 count=23");
                result = result.split("\n")[0];
                WriterUtils.postSuccess("Current host: " + result);

                WriterUtils.postInfo("Patching key...");

                success = writePayload(Utils.hexToBuffer(mKey));
                if (success) {
                    execShellCmd("dd if=tmp of=libg.so seek=5324832 obs=1 conv=notrunc");

                    execShellCmd("adb push libg.so /sdcard/");
                    execShellCmd("adb shell su -c cp /sdcard/libg.so /data/data/com.supercell.clashroyale/lib/");

                    WriterUtils.postSuccess("Clash Royale patched...");
                } else {
                    WriterUtils.postError("Something went wrong while creating patch mods.");
                }
            } else {
                WriterUtils.postError("Something went wrong while creating patch mods.");
            }
        } else {
            WriterUtils.postError("Looks like Clash Royale is not installed on your phone or not accessible.");
        }
    }
}
