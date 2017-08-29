package com.scdevteam;

import com.scdevteam.commands.BaseCommand;
import com.scdevteam.commands.Help;

public class CommandsHandler {

    public static BaseCommand handleCommand(String cmd) {
        String[] parts = cmd.split(" ");

        switch (parts[0]) {
            case "help":
                return new Help();
        }

        return null;
    }
}
