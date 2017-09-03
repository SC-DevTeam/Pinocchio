package com.scdevteam.crypto.rc4;

/**
 * Created by igio90 on 17/07/17.
 */
public class RC4 {
    public RC4(byte[] key) {
        setKey(key);
    }
    private byte[] state = new byte[256];
    private int x, y;

    public void setKey(byte[] key) {
        x = y = 0;

        for (int i = 0; i < 256; ++i) {
            state[i] = (byte) i;
        }

        for (int i = 0, j = 0, k = 0; i < 256; ++i) {
            k = (key[j] + state[i] + k) & 0xff;
            byte temp = state[i]; state[i] = state[k]; state[k] = temp;
            j = (j + 1) % key.length;
        }
    }

    public byte[] encrypt(byte[] clearText) {
        byte[] cipherText = new byte[clearText.length];
        for (int i = 0; i < clearText.length; ++i) {
            cipherText[i] = (byte) (clearText[i] ^ state[next()]);
        }
        return cipherText;
    }

    private int next() {
        byte temp;
        x = (x + 1) & 0xff;
        y = (y + state[x]) & 0xff;
        temp = state[x]; state[x] = state[y]; state[y] = temp;
        return (state[x] + state[y]) & 0xff;
    }

    public byte[] generate(int length) {
        byte[] key = new byte[length];
        for (int i = 0; i < key.length; i++) {
            key[i] = (byte) next();
        }
        return key;
    }

    public void skip(int length) {
        for (int i = 0; i < length; i++) {
            next();
        }
    }
}