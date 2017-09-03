package com.scdevteam.commands;

import com.scdevteam.WriterUtils;
import com.scdevteam.proto.HostsMap;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AndroidHostsEditor extends BaseCommand {

    private String mDNS;
    private String mIP;

    public AndroidHostsEditor add(String ip, String dns) {
        mIP = ip;
        mDNS = dns;
        return this;
    }

    public AndroidHostsEditor remove(String dns) {
        mDNS = dns;
        return this;
    }

    public AndroidHostsEditor scHosts() {
        WriterUtils.postAwesome("Clash Royale");
        WriterUtils.postInfo("Prod: " + HostsMap.PROD_CLASH_ROYALE);
        WriterUtils.postInfo("Stage: " + HostsMap.STAGE_CLASH_ROYALE);
        WriterUtils.post("");
        WriterUtils.postAwesome("Clash of Clans");
        WriterUtils.postInfo("Prod: " + HostsMap.PROD_CLASH_OF_CLANS);
        WriterUtils.postInfo("Stage: " + HostsMap.STAGE_CLASH_OF_CLANS);
        WriterUtils.post("");
        WriterUtils.postAwesome("Boom Beach");
        WriterUtils.postInfo("Prod: " + HostsMap.PROD_BOOM_BEACH);
        WriterUtils.postInfo("Stage: " + HostsMap.STAGE_BOOM_BEACH);
        WriterUtils.post("");
        WriterUtils.postAwesome("HayDay");
        WriterUtils.postInfo("Prod: " + HostsMap.PROD_HAY_DAY);
        WriterUtils.postInfo("Stage: " + HostsMap.STAGE_HAY_DAY);
        WriterUtils.post("");
        return this;
    }

    @Override
    public void execute() {
        if (mDNS != null) {
            boolean isRemove = mIP == null;
            String hosts = pullHosts();

            if (hosts != null) {
                if (isRemove) {
                    StringBuilder newHost = new StringBuilder();
                    String[] lines = hosts.replaceAll("\\s{2,}", " ").trim().split("\n");
                    for (String line : lines) {
                        String[] p = line.replaceAll("\\s{2,}", " ").trim().split(" ");
                        if (!p[1].equals(mDNS)) {
                            newHost.append(p[0]).append(" ").append(p[1]).append("\n");
                        }
                    }
                    hosts = newHost.toString();
                } else {
                    if (!hosts.endsWith("\n")) {
                        hosts += "\n";
                    }
                    hosts += mIP + " " + mDNS;
                }

                try {
                    FileUtils.writeStringToFile(new File("hosts"), hosts);
                    WriterUtils.postSuccess("Hosts edited with success...");
                    pushHosts();
                } catch (IOException e) {
                    WriterUtils.postSuccess("Failed to write new hosts...");
                }
            }
        }
    }

    private String pullHosts() {
        String result = execShellCmd("adb shell su -c cp /etc/hosts /sdcard/");
        if (result.isEmpty()) {
            WriterUtils.postSuccess("Pulling hosts...");
            execShellCmd("adb pull /sdcard/hosts");
            WriterUtils.postSuccess("Reading hosts...");
            InputStream is;
            try {
                is = new FileInputStream("hosts");
                return IOUtils.toString(is);
            } catch (IOException e) {
                WriterUtils.postError("Unable to read hosts file.");
                return null;
            }
        }

        WriterUtils.postError("I'm unable to pull hosts file from your device /etc/. Make sure your device is plugged in and ADB is working fine.");
        return null;
    }

    private void pushHosts() {
        WriterUtils.postSuccess("Uploading hosts...");
        execShellCmd("adb push hosts /sdcard/");
        execShellCmd("adb shell su -c mount -o remount,rw /system");
        execShellCmd("adb shell su -c mount -o rw,remount /system");
        execShellCmd("adb shell su -c cp /sdcard/hosts /etc/hosts");

        WriterUtils.postAwesome("New hosts uploaded.");
    }
}
