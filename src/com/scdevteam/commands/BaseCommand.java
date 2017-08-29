package com.scdevteam.commands;

public abstract class BaseCommand {

    public boolean mIsRunning = true;

    public BaseCommand() {
        new Thread(() -> {
            // Execute the command sync
            execute();

            // Release
            mIsRunning = false;
        }).start();
    }

    public abstract void execute();

    public boolean isRunning() {
        return mIsRunning;
    }
}
