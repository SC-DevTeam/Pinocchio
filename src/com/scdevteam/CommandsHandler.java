package com.scdevteam;

import com.scdevteam.commands.*;
import org.jetbrains.annotations.Nullable;

class CommandsHandler {

    @Nullable
    static BaseCommand getCommand(String cmd) {
        String[] parts = cmd.split(" ");

        switch (parts[0]) {
            case "help":
                return new Help();
            case "patch":
                if (parts.length < 2) {
                    return new MissingParams("Usage: patch [cr - coc - bb - hh - bs]");
                }
                return new Patchers(parts[1]);
            case "proxy":
                if (parts.length < 2) {
                    return new MissingParams("Usage: patch [cr - coc - bb - hh - bs]");
                }

                return new Proxies(parts[1]);
        }

        return null;
    }
}
