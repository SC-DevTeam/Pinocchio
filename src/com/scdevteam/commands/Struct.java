package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class Struct extends BaseCommand {

    private final String[] mParts;

    public Struct(String[] parts) {
        mParts = parts;
    }

    @Override
    public void execute() {
        String baseCmd = mParts[1];

        switch (baseCmd) {
            case "init":
                break;
            case "commit":
                break;
            case "field":
                break;
            default:
                WriterUtils.postError("struct [init - commit]");
                WriterUtils.postError("struct field [add - remove]");
                break;
        }
    }
}
