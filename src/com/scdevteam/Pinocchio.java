package com.scdevteam;

import com.scdevteam.commands.BaseCommand;

import java.util.Scanner;

public class Pinocchio {

    private final Object mPinocchioLocker = new Object();

    public void init() {
        StringBuilder bannerBuilder = new StringBuilder();
        for (String s : Banners.getBanner()) {
            bannerBuilder.append(s);
        }
        WriterUtils.post(new String(Utils.hexToBuffer(bannerBuilder.toString())));

        // Begin of configs
        WriterUtils.post("");

        WriterUtils.post("");
        WriterUtils.post("Type help to begin");
        WriterUtils.post("");
        setupStdin();
    }

    private void setupStdin() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);

            while (true) {
                writeMarker();
                String input = scanner.nextLine();
                if (input.trim().isEmpty()) {
                    continue;
                }
                if (input.equals("q") || input.equals("quit") || input.equals("exit")) {
                    System.exit(0);
                    return;
                }

                BaseCommand baseCommand = CommandsHandler.getCommand(input);
                if (baseCommand != null) {
                    baseCommand.init(this);
                }

                if (baseCommand == null) {
                    WriterUtils.postInfo("Command not found!");
                } else {
                    while (baseCommand.isRunning()) {
                        synchronized (mPinocchioLocker) {
                            try {
                                mPinocchioLocker.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static void writeMarker() {
        System.out.print(WriterUtils.RED_BOLD + "$> " + WriterUtils.RESET);
    }

    public Object getLocker() {
        return mPinocchioLocker;
    }
}
