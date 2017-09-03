package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Help extends BaseCommand {

    @Override
    public void execute() {
        WriterUtils.postSuccess("Pinocchio commands");
        WriterUtils.post("");
        WriterUtils.postError("Patchers");
        WriterUtils.postSuccess("patch [cr - coc - bb - hh - bs]");
        WriterUtils.post("");
        WriterUtils.postError("Proxies");
        WriterUtils.postSuccess("proxy [cr - coc - bb - hh - bs]");
        WriterUtils.post("");
        WriterUtils.postInfo("Android Utilities");
        WriterUtils.postSuccess("hosts add [ip] [dns]");
        WriterUtils.postSuccess("hosts remove [dns]");
        WriterUtils.postSuccess("hosts supercell");
        WriterUtils.post("");
        WriterUtils.postAwesome("Get involved! Join our discord: https://discord.gg/hTVhy3V");
    }
}
