package com.scdevteam.commands;

import com.scdevteam.Pinocchio;
import com.scdevteam.WriterUtils;

import java.io.*;

public abstract class BaseCommand {

    public boolean mIsRunning = true;

    public void init(Pinocchio pinocchio) {
        new Thread(() -> {
            // Execute the command sync
            execute();

            // Release
            mIsRunning = false;

            synchronized (pinocchio.getLocker()) {
                pinocchio.getLocker().notifyAll();
            }
        }).start();
    }

    public abstract void execute();

    String ddExtract(int decimalOffset, int count) {
        return execShellCmd("dd if=libg.so skip=" + decimalOffset + " bs=1 count=" + count);
    }

    String ddPatch(int decimalOffset) {
        return execShellCmd("dd if=tmp of=libg.so seek=" + decimalOffset + " obs=1 conv=notrunc");
    }

    /**
     * Exec a shell cmd
     *
     * @param cmd the command to execute
     * @return the std out with the result or error
     */
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

    /**
     * Write a payload into tmp file
     *
     * @param payload the payload
     * @return whether if the file has been written
     */
    boolean writePayload(String payload) {
        return writePayload(payload.getBytes());
    }

    boolean writePayload(byte[] payload) {
        try {
            FileOutputStream fos = new FileOutputStream("tmp");
            fos.write(payload);
            fos.close();
            return true;
        } catch (IOException e) {
            WriterUtils.postError("Something went wrong while creating patch mods.");
            return false;
        }
    }

    public boolean isRunning() {
        return mIsRunning;
    }
}
