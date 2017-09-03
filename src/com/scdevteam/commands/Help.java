package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Help extends BaseCommand {

    @Override
    public void execute() {
        WriterUtils.postSuccess(WriterUtils.buildPurpleBold("P") +
                WriterUtils.buildGreenBold("I") +
                WriterUtils.buildYellowBold("N") +
                WriterUtils.buildCyanBold("O") +
                WriterUtils.buildRedBold("C") +
                WriterUtils.buildPurpleBold("C") +
                WriterUtils.buildGreenBold("H") +
                WriterUtils.buildYellowBold("I") +
                WriterUtils.buildCyanBold("O")
        );
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Patchers"));
        WriterUtils.postSuccess("patch [cr - coc - bb - hh - bs]");
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Proxies"));
        WriterUtils.postSuccess("proxy [cr - coc - bb - hh - bs]");
        WriterUtils.postAwesome(WriterUtils.buildRedBold("--hex") + " hexdump decrypted payloads");
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Android Utilities"));
        WriterUtils.postSuccess("hosts add [ip] [dns]");
        WriterUtils.postSuccess("hosts remove [dns]");
        WriterUtils.postSuccess("hosts supercell");
        WriterUtils.post("");
        WriterUtils.postAwesome(WriterUtils.buildGreenBold("Get involved!") +
                " Join our discord: https://discord.gg/hTVhy3V");
    }
}
