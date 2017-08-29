package com.scdevteam.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

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

    String execShellCmd(String cmd) {
        StringBuilder output = new StringBuilder();
        Process process;
        try {
            process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line)
                        .append("\n");
            }
            reader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = reader.readLine()) != null) {
                output.append(line)
                        .append("\n");
            }
        } catch (Exception e) {
        }

        return output.toString();
    }

    public boolean isRunning() {
        return mIsRunning;
    }
}
