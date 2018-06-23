package com.scdevteam.commands;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;

public class Settings {
    private static Settings sInstance;

    private JSONObject mSettingsObject;

    public static Settings getInstance() {
        if (sInstance == null) {
            sInstance = new Settings();
        }

        return sInstance;
    }

    private Settings() {
        File file = new File("settings.json");
        if (file.exists()) {
            InputStream is;
            try {
                is = new FileInputStream("settings.json");
                String jsonTxt = IOUtils.toString(is);
                mSettingsObject = new JSONObject(jsonTxt);
            } catch (IOException e) {
                buildDefaults();
            }
        } else {
            buildDefaults();
        }
    }

    public void write(String key, String value) {

    }

    private void commit() {
        try {
            FileUtils.writeStringToFile(new File("settings.json"), mSettingsObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void buildDefaults() {
        mSettingsObject = new JSONObject();
        mSettingsObject.put("proxy_payload", 1);

        commit();
    }
}
