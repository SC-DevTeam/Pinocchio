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
                    case 1:
                        patchCoc();
                        break;
                    default:
                        WriterUtils.postError("This game is currently not supported by the patcher");
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
        String gameName = "Clash Royale";
        String gamePackage = "com.supercell.clashroyale";
        int hostLength = 23;

        if (mHost.length() != hostLength) {
            WriterUtils.postError("Host must be " + hostLength + " character length");
            return;
        }

        if (pullLib(gamePackage, gameName)) {
            String result = ddExtract(4412475, hostLength);
            result = result.split("\n")[0];
            WriterUtils.postSuccess("Current host: " + result);

            WriterUtils.postInfo("Patching with new host: " + mHost);

            boolean success = writePayload(mHost);

            if (success) {
                ddPatch(4412475);

                WriterUtils.postInfo("Patching key...");

                success = writePayload(Utils.hexToBuffer(mKey));
                if (success) {
                    ddPatch(5324832);

                    finalizePatch(gameName, gamePackage);
                }
            }
        }
    }

    private void patchCoc() {
        String gameName = "Clash of Clans";
        String gamePackage = "com.supercell.clashofclans";
        int hostLength = 22;

        if (mHost.length() != hostLength) {
            WriterUtils.postError("Host must be " + hostLength + " character length");
            return;
        }

        if (pullLib(gamePackage, gameName)) {
            String result = ddExtract(4828203, hostLength);
            result = result.split("\n")[0];
            WriterUtils.postSuccess("Current host: " + result);

            WriterUtils.postInfo("Patching with new host: " + mHost);

            boolean success = writePayload(mHost);

            if (success) {
                ddPatch(4828203);

                WriterUtils.postInfo("Patching server key...");

                success = writePayload(Utils.hexToBuffer("72f1a4a4c48e44da0c42310f800e96624e6dc6a641a9d41c3b5039d8dfadc27e"));
                if (success) {
                    ddPatch(5759024);

                    WriterUtils.postInfo("Patching magic key...");
                    success = writePayload(Utils.hexToBuffer("14"));
                    if (success) {
                        ddPatch(4248312);
                        ddPatch(4251436);
                    }

                    finalizePatch(gameName, gamePackage);
                }
            }
        }
    }

    private boolean pullLib(String gamePackageName, String gameName) {
        String result = execShellCmd("adb shell su -c cp /data/data/" + gamePackageName + "/lib/libg.so /sdcard/");
        if (result.isEmpty()) {
            WriterUtils.postSuccess(gameName + " found. Pulling libg...");
            execShellCmd("adb pull /sdcard/libg.so");
            return true;
        }

        WriterUtils.postError("Looks like " + gameName + " is not installed on your phone or not accessible.");
        return false;
    }

    private void finalizePatch(String gameName, String gamePackage) {
        WriterUtils.postSuccess(gameName + " patched...");

        WriterUtils.postSuccess("Uploading patched libg...");
        execShellCmd("adb push libg.so /sdcard/");
        execShellCmd("adb shell su -c cp /sdcard/libg.so /data/data/" + gamePackage + "/lib/");

        WriterUtils.postAwesome(gameName + " ready for Pinocchio proxy.");
    }
}
