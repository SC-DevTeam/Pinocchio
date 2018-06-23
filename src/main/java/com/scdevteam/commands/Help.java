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
        WriterUtils.postSuccess("patch [cr - coc - bb - hd - bs]");
        WriterUtils.postAwesome(WriterUtils.buildRedBold("--tv") + " extra patch for CR TV Royale to show results before watching battles");
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Proxies"));
        WriterUtils.postSuccess("proxy [cr - coc - bb - hd - bs]");
        WriterUtils.postAwesome(WriterUtils.buildRedBold("--hex") + " hexdump decrypted payloads");
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Android Utilities"));
        WriterUtils.postSuccess("hosts add [ip] [dns]");
        WriterUtils.postSuccess("hosts remove [dns]");
        WriterUtils.postSuccess("hosts supercell");
        WriterUtils.post("");
        WriterUtils.postError(WriterUtils.buildGreenBold("Protocol Utilities"));
        WriterUtils.postSuccess("parser [hexpayload] [offset - optional]");
        WriterUtils.post("");
        WriterUtils.post("");
        WriterUtils.postAwesome(WriterUtils.buildGreenBold("Get involved!") +
                " Join our discord: https://discord.gg/hTVhy3V");
    }
}
