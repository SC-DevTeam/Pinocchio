package com.scdevteam;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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

    private static void setupStdin() {
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

                // Handle the input
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
}
