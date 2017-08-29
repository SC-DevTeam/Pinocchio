package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

public class MissingParams extends BaseCommand {
    private final String mHint;

    public MissingParams(String hint) {
        mHint = hint;
    }

    @Override
    public void execute() {
        WriterUtils.postError(mHint);
    }
}
