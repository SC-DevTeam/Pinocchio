package com.scdevteam;

import com.scdevteam.commands.BaseCommand;
import com.scdevteam.commands.Help;
import com.scdevteam.commands.MissingParams;
import com.scdevteam.commands.Patchers;

public class CommandsHandler {

    public static BaseCommand handleCommand(String cmd) {
        String[] parts = cmd.split(" ");

        switch (parts[0]) {
            case "help":
                return new Help();
            case "patch":
                if (parts.length != 4) {
                    return new MissingParams("Usage: patch [cr - coc - bb - hh - bs] [host] [key]");
                }

                return new Patchers(parts[1], parts[2], parts[3]);
        }

        return null;
    }
}
