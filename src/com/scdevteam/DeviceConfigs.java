package com.scdevteam;

public class DeviceConfigs {
    public static final String APIKEY = "MzQ0MDUxMzc0NTc0NjY1NzI4.DGn58Q.aNNmayFy4hE-qD2g-xZm5QsX2vc";

    public static final String DEVICE = "Android SDK built for x86";
    public static final String OS = "7.0";
    public static final String LANG = "it-IT";
    public static String ADVERTISING_ID;
    public static String OPEN_UDID;

    static {
        ADVERTISING_ID = Utils.getRandomHexString(32);
        ADVERTISING_ID = ADVERTISING_ID.substring(0, 7) + "-" +
                ADVERTISING_ID.substring(7, 11) + "-" +
                ADVERTISING_ID.substring(11, 15) + "-" +
                ADVERTISING_ID.substring(15, 19) + "-" +
                ADVERTISING_ID.substring(19);
        OPEN_UDID = Utils.getRandomHexString(16);
    }
}
