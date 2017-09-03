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
                    return new MissingParams("Usage: proxy [cr - coc - bb - hh - bs]");
                }

                return new Proxies(parts[1]);
            case "hosts":
                if (parts.length < 2) {
                    return new MissingParams("hosts {add [ip] [dns]} {remove [dns]} {supercell}");

                }

                switch (parts[1]) {
                    case "supercell":
                        return new AndroidHostsEditor().scHosts();
                    case "add":
                        if (parts.length != 4) {
                            return new MissingParams("hosts add [ip] [dns]");
                        }
                        return new AndroidHostsEditor().add(parts[2], parts[3]);
                    case "remove":
                        if (parts.length != 3) {
                            return new MissingParams("hosts remove [dns]");
                        }
                        return new AndroidHostsEditor().remove(parts[2]);
                }
        }

        return null;
    }
}
