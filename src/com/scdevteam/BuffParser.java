package com.scdevteam;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class BuffParser {

    private final byte[] mPayload;
    private ByteBuffer mByteBuffer;

    public BuffParser(byte[] payload, int offset) {
        mPayload = Arrays.copyOfRange(payload, offset, payload.length);
        mByteBuffer = ByteBuffer.wrap(payload);
    }

    public String toDiscordResponse() {
        StringBuilder res = new StringBuilder();
        parseInt(res);
        parseLong(res);
        parseBoolean(res);
        parseString(res);
        parseRrsInt(res);
        return res.toString();
    }

    private void parseLong(StringBuilder res) {
        res.append("LONG:");
        res.append("\n");
        try {
            byte[] z = new byte[8];
            res.append("hex: ");
            ByteBuffer p = ByteBuffer.wrap(mPayload);
            p.get(z, 0, 8);
            res.append(Utils.toHexString(z));
            p = ByteBuffer.wrap(z);
            SLong sLong = new SLong(p);
            res.append("\n");
            res.append("high: ");
            res.append(sLong.hi);
            res.append("\n");
            res.append("low: ");
            res.append(sLong.lo);
            res.append("\n");
            res.append("value: ");
            res.append(sLong.v);
        } catch (Exception ignored) {
            res.append("0");
        }
        res.append("\n\n");
    }

    private void parseInt(StringBuilder res) {
        res.append("INT32:");
        res.append("\n");
        try {
            byte[] z = new byte[4];
            res.append("hex: ");
            ByteBuffer p = ByteBuffer.wrap(mPayload);
            p.get(z, 0, 4);
            res.append(Utils.toHexString(z));
            p = ByteBuffer.wrap(z);
            res.append("\n");
            int r = p.getInt();
            res.append("signed: ");
            res.append(r);
            res.append("\n");
            res.append("unsigned: ");
            res.append(r & 0xFFFFFF);
        } catch (Exception ignored) {
            res.append("0");
        }
        res.append("\n\n");
    }

    private void parseBoolean(StringBuilder res) {
        res.append("BOOLEAN:");
        res.append("\n");
        try {
            byte[] z = new byte[1];
            res.append("hex: ");
            ByteBuffer p = ByteBuffer.wrap(mPayload);
            p.get(z, 0, 1);
            res.append(Utils.toHexString(z));
            p = ByteBuffer.wrap(z);
            res.append("\n");
            res.append("value: ");
            byte bool = p.get();
            res.append(bool > (byte) 1 ? "-" : bool == (byte) 1);
        } catch (Exception ignored) {
            res.append("0");
        }
        res.append("\n\n");
    }

    private void parseString(StringBuilder res) {
        res.append("STRING:");
        res.append("\n");
        try {
            ByteBuffer p = ByteBuffer.wrap(mPayload);
            int len = (int) (p.getInt() & 0x00000000ffffffffL);
            res.append("len: ");
            res.append(len);
            res.append("\n");

            if (len > p.remaining()) {
                res.append("value: -");
            } else {
                byte[] z = new byte[len];
                p.get(z, 0, len);
                res.append("value: ");
                res.append(new String(z));
            }
        } catch (Exception ignored) {
            res.append("-");
        }
        res.append("\n\n");
    }

    private void parseRrsInt(StringBuilder res) {
        res.append("RRSINT32:");
        res.append("\n");
        try {
            ByteBuffer p = ByteBuffer.wrap(mPayload);
            Utils.RrsInt32 rrsInt32 = Utils.readRrsInt32(p);

            byte[] z = new byte[rrsInt32.length];
            p = ByteBuffer.wrap(mPayload);
            res.append("hex: ");
            p.get(z, 0, rrsInt32.length);
            res.append(Utils.toHexString(z));

            res.append("\n");
            res.append("signed: ");
            res.append(rrsInt32.value);
            res.append("\n");
            res.append("unsigned: ");
            res.append(rrsInt32.value & 0xFFFFFFF);
            res.append("\n");
            res.append("length: ");
            res.append(rrsInt32.length);
        } catch (Exception ignored) {
            ignored.printStackTrace();
            res.append("\n");
            res.append("-");
        }
        res.append("\n\n");
    }

    public int readInt() {
        return mByteBuffer.getInt();
    }

    public SLong readLong() {
        byte[] l = new byte[8];
        mByteBuffer.get(l, 0, 8);
        return new SLong(ByteBuffer.wrap(l));
    }

    public boolean readBoolean() {
        return mByteBuffer.get() > 0;
    }

    public String readString() {
        int len = readInt();
        if (len > mByteBuffer.remaining()) {
            return "Not a string?";
        }
        byte[] s = new byte[len];
        mByteBuffer.get(s, 0, len);
        return new String(s);
    }

    public RrsInt readRssInt32() {
        int c = 0;
        int v = 0;
        int seventh;
        int msb;
        int b;

        if (mByteBuffer.remaining() < 5) {
            byte[] s = Arrays.copyOf(mByteBuffer.array(), 5);
            mByteBuffer = ByteBuffer.wrap(s);
        }

        do {
            b = mByteBuffer.get();
            if (c == 0) {
                seventh = (b & 0x40) >> 6; // save 7th bit
                msb = (b & 0x80) >> 7; // save msb
                b = b << 1; // rotate to the left
                b = b & ~(0x181); // clear 8th and 1st bit and 9th if any
                b = b | (msb << 7) | (seventh); // insert msb and 6th back in
            }

            v |= (b & 0x7f) << (7 * c);
            ++c;
        } while ((b & 0x80) != 0);
        v = ((v >>> 1) ^ -(v & 1));
        return new RrsInt(c, v);
    }

    public boolean hasRemaining() {
        return mByteBuffer.hasRemaining();
    }

    public byte[] remaining() {
        byte[] r = new byte[mByteBuffer.remaining()];
        mByteBuffer.get(r, 0, r.length);
        return r;
    }

    public ByteBuffer cloneBuffer() {
        return mByteBuffer.duplicate();
    }

    public static class SLong {
        public final int hi;
        public final int lo;
        public final long v;
        public SLong(ByteBuffer b) {
            b.rewind();
            v = b.getLong();
            b.rewind();
            hi = b.getInt();
            lo = b.getInt();
        }
    }

    public static class RrsInt {
        public int len;
        public int val;
        public RrsInt(int len, int val) {
            this.len = len;
            this.val = val;
        }
    }
}
