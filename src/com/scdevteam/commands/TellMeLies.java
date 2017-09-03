package com.scdevteam.commands;

import com.scdevteam.WriterUtils;

import java.util.Random;

public class TellMeLies extends BaseCommand {

    private String[] Lies = {
            "UCS is well written.",
            "Pinocchio sucks.",
            "iGio is a pro."
    };

    @Override
    public void execute() {
        Random Random = new Random();
        int RndIndex = Random.nextInt(Lies.length);

        WriterUtils.post(this.Lies[RndIndex]);
    }
}
