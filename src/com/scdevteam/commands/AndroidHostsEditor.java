package com.scdevteam.commands;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AndroidHostsEditor extends BaseCommand {

    private final LinkedHashMap<String, String> mChanges = new LinkedHashMap<>();

    public AndroidHostsEditor add(String ip, String dns) {
        mChanges.put(dns, ip);
        return this;
    }

    public AndroidHostsEditor remove(String dns) {
        mChanges.put(dns, dns);
        return this;
    }

    public AndroidHostsEditor scHosts() {

        return this;
    }

    @Override
    public void execute() {
        // Do more shits
    }
}
