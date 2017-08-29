package com.scdevteam;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

public class Utils {
    private static final char[] sHexTable = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

    public static int toInt32(byte[] b) {
        return ByteBuffer.wrap(b).order(ByteOrder.BIG_ENDIAN).getInt();
    }

    public static int toInt24(byte[] b) {
        return (b[0] & 0xFF) << 16 | (b[1] & 0xFF) << 8 | (b[2] & 0xFF);
    }

    public static int toInt16(byte[] b) {
        return ByteBuffer.wrap(b).order(ByteOrder.BIG_ENDIAN).getShort();
    }

    public static byte[] fromLong(long l) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
        byteBuffer.putLong(l);
        return byteBuffer.array();
    }

    public static byte[] fromInt32(int i) {
        byte[] result = new byte[4];

        result[0] = (byte) (i >> 24);
        result[1] = (byte) (i >> 16);
        result[2] = (byte) (i >> 8);
        result[3] = (byte) (i);

        return result;
    }

    public static byte[] fromInt24(int i) {
        byte[] result = new byte[3];

        result[0] = (byte) (i >> 16);
        result[1] = (byte) (i >> 8);
        result[2] = (byte) (i);

        return result;
    }

    public static byte[] fromInt16(int i) {
        byte[] result = new byte[2];

        result[0] = (byte) (i >> 8);
        result[1] = (byte) (i);

        return result;
    }

    public static String toHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length*2];
        int v;

        for(int j=0; j < bytes.length; j++) {
            v = bytes[j] & 0xFF;
            hexChars[j*2] = sHexTable[v>>>4];
            hexChars[j*2 + 1] = sHexTable[v & 0x0F];
        }

        return new String(hexChars);
    }

    public static byte[] hexToBuffer(String s) {
        int len = s.length();
        byte[] data = new byte[len/2];

        for(int i = 0; i < len; i+=2){
            data[i/2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
        }

        return data;
    }

    public static String getRandomHexString(int length) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        while(sb.length() < length){
            sb.append(Integer.toHexString(r.nextInt()));
        }

        return sb.toString().substring(0, length);
    }

    public static int readInt32(byte[] input) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(input);
        return byteBuffer.getInt();
    }

    public static short readShort(byte[] input) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(input);
        return byteBuffer.getShort();
    }

    public static long readLong(byte[] input) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(input);
        return byteBuffer.getLong();
    }

    public static String readString(byte[] input) {
        return new String(input);
    }

    public static Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static String getString(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            return "";
        }
    }

    public static int getInt(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public static long getLong(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getLong(key);
        } catch (JSONException e) {
            return -1;
        }
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String key) {
        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            return null;
        }
    }

    public static boolean getBoolean(JSONObject jsonObject, String key, boolean def) {
        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            return def;
        }
    }

    public static String formatURLParams(String param) {
        try {
            URL url = new URL("https://sarahah.com/?" + param);
            URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
            return uri.getRawQuery();
        } catch (MalformedURLException | URISyntaxException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String expandUrl(String shortenedUrl) {
        try {
            URL url = new URL(shortenedUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection(Proxy.NO_PROXY);
            httpURLConnection.setInstanceFollowRedirects(false);
            String expandedURL = httpURLConnection.getHeaderField("Location");
            httpURLConnection.disconnect();
            return expandedURL;
        } catch (Exception e) {
            return null;
        }
    }

    public static void log(String s) {
        System.out.print(s + "\n");
    }
}
