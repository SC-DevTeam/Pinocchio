package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Help extends BaseCommand {

    @Override
    public void execute() {
        WriterUtils.postSuccess("Pinocchio commands");
        WriterUtils.post("");
        WriterUtils.postError("Patchers");
        WriterUtils.postSuccess("patch [cr - coc - bb - hh - bs] [host] [key]");
    }
}
