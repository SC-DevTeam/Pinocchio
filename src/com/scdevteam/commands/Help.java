package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Help extends BaseCommand {

    @Override
    public void execute() {
        WriterUtils.postSuccess("Pinocchio commands");
        WriterUtils.post("");
        WriterUtils.postError("Patchers");
        WriterUtils.postSuccess("patch cr");
        WriterUtils.postSuccess("patch coc");
        WriterUtils.postSuccess("patch bb");
        WriterUtils.postSuccess("patch hh");
        WriterUtils.postSuccess("patch bs");
    }
}
