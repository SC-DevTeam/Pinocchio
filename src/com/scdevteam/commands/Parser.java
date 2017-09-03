package com.scdevteam.commands;

import com.scdevteam.WriterUtils;
import com.scdevteam.proto.BuffParser;

public class Parser extends BaseCommand {

    private final byte[] mBuffer;
    private final int mOffset;

    public Parser(byte[] buffer, int offset) {
        mBuffer = buffer;
        mOffset = offset;
    }

    @Override
    public void execute() {
        BuffParser parser = new BuffParser(mBuffer, mOffset);
        WriterUtils.post(parser.toString());
    }
}
