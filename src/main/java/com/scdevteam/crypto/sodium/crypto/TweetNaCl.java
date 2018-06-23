package com.scdevteam.crypto.sodium.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

class Curve25519 {
    private static byte[] basePoint = new byte[]{9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    public Curve25519() {
    }

    private static void feCSwap(Curve25519.FieldElement f, Curve25519.FieldElement g, long b) {
        Curve25519.FieldElement x = new Curve25519.FieldElement();
        b = -b;

        int i;
        for(i = 0; i < x.arr.length; ++i) {
            x.arr[i] = b & (f.arr[i] ^ g.arr[i]);
        }

        long[] var10000;
        for(i = 0; i < f.arr.length; ++i) {
            var10000 = f.arr;
            var10000[i] ^= x.arr[i];
        }

        for(i = 0; i < g.arr.length; ++i) {
            var10000 = g.arr;
            var10000[i] ^= x.arr[i];
        }

    }

    private static Curve25519.FieldElement feSub(Curve25519.FieldElement a, Curve25519.FieldElement b) {
        Curve25519.FieldElement retVal = new Curve25519.FieldElement();

        for(int i = 0; i < retVal.arr.length; ++i) {
            retVal.arr[i] = a.arr[i] - b.arr[i];
        }

        return retVal;
    }

    private static Curve25519.FieldElement feAdd(Curve25519.FieldElement a, Curve25519.FieldElement b) {
        Curve25519.FieldElement retVal = new Curve25519.FieldElement();

        for(int i = 0; i < retVal.arr.length; ++i) {
            retVal.arr[i] = a.arr[i] + b.arr[i];
        }

        return retVal;
    }

    private static Curve25519.FieldElement feMul(Curve25519.FieldElement f, Curve25519.FieldElement g) {
        Curve25519.FieldElement retVal = new Curve25519.FieldElement();
        long f0 = f.arr[0];
        long f1 = f.arr[1];
        long f2 = f.arr[2];
        long f3 = f.arr[3];
        long f4 = f.arr[4];
        long f5 = f.arr[5];
        long f6 = f.arr[6];
        long f7 = f.arr[7];
        long f8 = f.arr[8];
        long f9 = f.arr[9];
        long g0 = g.arr[0];
        long g1 = g.arr[1];
        long g2 = g.arr[2];
        long g3 = g.arr[3];
        long g4 = g.arr[4];
        long g5 = g.arr[5];
        long g6 = g.arr[6];
        long g7 = g.arr[7];
        long g8 = g.arr[8];
        long g9 = g.arr[9];
        long g1_19 = 19L * g1;
        long g2_19 = 19L * g2;
        long g3_19 = 19L * g3;
        long g4_19 = 19L * g4;
        long g5_19 = 19L * g5;
        long g6_19 = 19L * g6;
        long g7_19 = 19L * g7;
        long g8_19 = 19L * g8;
        long g9_19 = 19L * g9;
        long f1_2 = 2L * f1;
        long f3_2 = 2L * f3;
        long f5_2 = 2L * f5;
        long f7_2 = 2L * f7;
        long f9_2 = 2L * f9;
        long f0g0 = f0 * g0;
        long f0g1 = f0 * g1;
        long f0g2 = f0 * g2;
        long f0g3 = f0 * g3;
        long f0g4 = f0 * g4;
        long f0g5 = f0 * g5;
        long f0g6 = f0 * g6;
        long f0g7 = f0 * g7;
        long f0g8 = f0 * g8;
        long f0g9 = f0 * g9;
        long f1g0 = f1 * g0;
        long f1g1_2 = f1_2 * g1;
        long f1g2 = f1 * g2;
        long f1g3_2 = f1_2 * g3;
        long f1g4 = f1 * g4;
        long f1g5_2 = f1_2 * g5;
        long f1g6 = f1 * g6;
        long f1g7_2 = f1_2 * g7;
        long f1g8 = f1 * g8;
        long f1g9_38 = f1_2 * g9_19;
        long f2g0 = f2 * g0;
        long f2g1 = f2 * g1;
        long f2g2 = f2 * g2;
        long f2g3 = f2 * g3;
        long f2g4 = f2 * g4;
        long f2g5 = f2 * g5;
        long f2g6 = f2 * g6;
        long f2g7 = f2 * g7;
        long f2g8_19 = f2 * g8_19;
        long f2g9_19 = f2 * g9_19;
        long f3g0 = f3 * g0;
        long f3g1_2 = f3_2 * g1;
        long f3g2 = f3 * g2;
        long f3g3_2 = f3_2 * g3;
        long f3g4 = f3 * g4;
        long f3g5_2 = f3_2 * g5;
        long f3g6 = f3 * g6;
        long f3g7_38 = f3_2 * g7_19;
        long f3g8_19 = f3 * g8_19;
        long f3g9_38 = f3_2 * g9_19;
        long f4g0 = f4 * g0;
        long f4g1 = f4 * g1;
        long f4g2 = f4 * g2;
        long f4g3 = f4 * g3;
        long f4g4 = f4 * g4;
        long f4g5 = f4 * g5;
        long f4g6_19 = f4 * g6_19;
        long f4g7_19 = f4 * g7_19;
        long f4g8_19 = f4 * g8_19;
        long f4g9_19 = f4 * g9_19;
        long f5g0 = f5 * g0;
        long f5g1_2 = f5_2 * g1;
        long f5g2 = f5 * g2;
        long f5g3_2 = f5_2 * g3;
        long f5g4 = f5 * g4;
        long f5g5_38 = f5_2 * g5_19;
        long f5g6_19 = f5 * g6_19;
        long f5g7_38 = f5_2 * g7_19;
        long f5g8_19 = f5 * g8_19;
        long f5g9_38 = f5_2 * g9_19;
        long f6g0 = f6 * g0;
        long f6g1 = f6 * g1;
        long f6g2 = f6 * g2;
        long f6g3 = f6 * g3;
        long f6g4_19 = f6 * g4_19;
        long f6g5_19 = f6 * g5_19;
        long f6g6_19 = f6 * g6_19;
        long f6g7_19 = f6 * g7_19;
        long f6g8_19 = f6 * g8_19;
        long f6g9_19 = f6 * g9_19;
        long f7g0 = f7 * g0;
        long f7g1_2 = f7_2 * g1;
        long f7g2 = f7 * g2;
        long f7g3_38 = f7_2 * g3_19;
        long f7g4_19 = f7 * g4_19;
        long f7g5_38 = f7_2 * g5_19;
        long f7g6_19 = f7 * g6_19;
        long f7g7_38 = f7_2 * g7_19;
        long f7g8_19 = f7 * g8_19;
        long f7g9_38 = f7_2 * g9_19;
        long f8g0 = f8 * g0;
        long f8g1 = f8 * g1;
        long f8g2_19 = f8 * g2_19;
        long f8g3_19 = f8 * g3_19;
        long f8g4_19 = f8 * g4_19;
        long f8g5_19 = f8 * g5_19;
        long f8g6_19 = f8 * g6_19;
        long f8g7_19 = f8 * g7_19;
        long f8g8_19 = f8 * g8_19;
        long f8g9_19 = f8 * g9_19;
        long f9g0 = f9 * g0;
        long f9g1_38 = f9_2 * g1_19;
        long f9g2_19 = f9 * g2_19;
        long f9g3_38 = f9_2 * g3_19;
        long f9g4_19 = f9 * g4_19;
        long f9g5_38 = f9_2 * g5_19;
        long f9g6_19 = f9 * g6_19;
        long f9g7_38 = f9_2 * g7_19;
        long f9g8_19 = f9 * g8_19;
        long f9g9_38 = f9_2 * g9_19;
        long h0 = f0g0 + f1g9_38 + f2g8_19 + f3g7_38 + f4g6_19 + f5g5_38 + f6g4_19 + f7g3_38 + f8g2_19 + f9g1_38;
        long h1 = f0g1 + f1g0 + f2g9_19 + f3g8_19 + f4g7_19 + f5g6_19 + f6g5_19 + f7g4_19 + f8g3_19 + f9g2_19;
        long h2 = f0g2 + f1g1_2 + f2g0 + f3g9_38 + f4g8_19 + f5g7_38 + f6g6_19 + f7g5_38 + f8g4_19 + f9g3_38;
        long h3 = f0g3 + f1g2 + f2g1 + f3g0 + f4g9_19 + f5g8_19 + f6g7_19 + f7g6_19 + f8g5_19 + f9g4_19;
        long h4 = f0g4 + f1g3_2 + f2g2 + f3g1_2 + f4g0 + f5g9_38 + f6g8_19 + f7g7_38 + f8g6_19 + f9g5_38;
        long h5 = f0g5 + f1g4 + f2g3 + f3g2 + f4g1 + f5g0 + f6g9_19 + f7g8_19 + f8g7_19 + f9g6_19;
        long h6 = f0g6 + f1g5_2 + f2g4 + f3g3_2 + f4g2 + f5g1_2 + f6g0 + f7g9_38 + f8g8_19 + f9g7_38;
        long h7 = f0g7 + f1g6 + f2g5 + f3g4 + f4g3 + f5g2 + f6g1 + f7g0 + f8g9_19 + f9g8_19;
        long h8 = f0g8 + f1g7_2 + f2g6 + f3g5_2 + f4g4 + f5g3_2 + f6g2 + f7g1_2 + f8g0 + f9g9_38;
        long h9 = f0g9 + f1g8 + f2g7 + f3g6 + f4g5 + f5g4 + f6g3 + f7g2 + f8g1 + f9g0;
        long[] carry = new long[10];
        carry[0] = h0 + 33554432L >> 26;
        h1 += carry[0];
        h0 -= carry[0] << 26;
        carry[4] = h4 + 33554432L >> 26;
        h5 += carry[4];
        h4 -= carry[4] << 26;
        carry[1] = h1 + 16777216L >> 25;
        h2 += carry[1];
        h1 -= carry[1] << 25;
        carry[5] = h5 + 16777216L >> 25;
        h6 += carry[5];
        h5 -= carry[5] << 25;
        carry[2] = h2 + 33554432L >> 26;
        h3 += carry[2];
        h2 -= carry[2] << 26;
        carry[6] = h6 + 33554432L >> 26;
        h7 += carry[6];
        h6 -= carry[6] << 26;
        carry[3] = h3 + 16777216L >> 25;
        h4 += carry[3];
        h3 -= carry[3] << 25;
        carry[7] = h7 + 16777216L >> 25;
        h8 += carry[7];
        h7 -= carry[7] << 25;
        carry[4] = h4 + 33554432L >> 26;
        h5 += carry[4];
        h4 -= carry[4] << 26;
        carry[8] = h8 + 33554432L >> 26;
        h9 += carry[8];
        h8 -= carry[8] << 26;
        carry[9] = h9 + 16777216L >> 25;
        h0 += carry[9] * 19L;
        h9 -= carry[9] << 25;
        carry[0] = h0 + 33554432L >> 26;
        h1 += carry[0];
        h0 -= carry[0] << 26;
        retVal.arr[0] = (long)((int)h0);
        retVal.arr[1] = (long)((int)h1);
        retVal.arr[2] = (long)((int)h2);
        retVal.arr[3] = (long)((int)h3);
        retVal.arr[4] = (long)((int)h4);
        retVal.arr[5] = (long)((int)h5);
        retVal.arr[6] = (long)((int)h6);
        retVal.arr[7] = (long)((int)h7);
        retVal.arr[8] = (long)((int)h8);
        retVal.arr[9] = (long)((int)h9);
        return retVal;
    }

    private static long int64(long x) {
        return x;
    }

    private static Curve25519.FieldElement feSquare(Curve25519.FieldElement f) {
        return f.square();
    }

    private static Curve25519.FieldElement feInvert(Curve25519.FieldElement z) {
        new Curve25519.FieldElement();
        Curve25519.FieldElement t0 = feSquare(z);

        int i;
        for(i = 1; i < 1; ++i) {
            t0 = feSquare(t0);
        }

        Curve25519.FieldElement t1 = feSquare(t0);

        for(i = 1; i < 2; ++i) {
            t1 = feSquare(t1);
        }

        t1 = feMul(z, t1);
        t0 = feMul(t0, t1);
        Curve25519.FieldElement t2 = feSquare(t0);

        for(i = 1; i < 1; ++i) {
            t2 = feSquare(t2);
        }

        t1 = feMul(t1, t2);
        t2 = feSquare(t1);

        for(i = 1; i < 5; ++i) {
            t2 = feSquare(t2);
        }

        t1 = feMul(t2, t1);
        t2 = feSquare(t1);

        for(i = 1; i < 10; ++i) {
            t2 = feSquare(t2);
        }

        t2 = feMul(t2, t1);
        Curve25519.FieldElement t3 = feSquare(t2);

        for(i = 1; i < 20; ++i) {
            t3 = feSquare(t3);
        }

        t2 = feMul(t3, t2);
        t2 = feSquare(t2);

        for(i = 1; i < 10; ++i) {
            t2 = feSquare(t2);
        }

        t1 = feMul(t2, t1);
        t2 = feSquare(t1);

        for(i = 1; i < 50; ++i) {
            t2 = feSquare(t2);
        }

        t2 = feMul(t2, t1);
        t3 = feSquare(t2);

        for(i = 1; i < 100; ++i) {
            t3 = feSquare(t3);
        }

        t2 = feMul(t3, t2);
        t2 = feSquare(t2);

        for(i = 1; i < 50; ++i) {
            t2 = feSquare(t2);
        }

        t1 = feMul(t2, t1);
        t1 = feSquare(t1);

        for(i = 1; i < 5; ++i) {
            t1 = feSquare(t1);
        }

        return feMul(t1, t0);
    }

    private static Curve25519.FieldElement feMul121666(Curve25519.FieldElement f) {
        long h0 = int64(f.arr[0]) * 121666L;
        long h1 = int64(f.arr[1]) * 121666L;
        long h2 = int64(f.arr[2]) * 121666L;
        long h3 = int64(f.arr[3]) * 121666L;
        long h4 = int64(f.arr[4]) * 121666L;
        long h5 = int64(f.arr[5]) * 121666L;
        long h6 = int64(f.arr[6]) * 121666L;
        long h7 = int64(f.arr[7]) * 121666L;
        long h8 = int64(f.arr[8]) * 121666L;
        long h9 = int64(f.arr[9]) * 121666L;
        long[] carry = new long[10];
        carry[9] = h9 + 16777216L >> 25;
        h0 += carry[9] * 19L;
        h9 -= carry[9] << 25;
        carry[1] = h1 + 16777216L >> 25;
        h2 += carry[1];
        h1 -= carry[1] << 25;
        carry[3] = h3 + 16777216L >> 25;
        h4 += carry[3];
        h3 -= carry[3] << 25;
        carry[5] = h5 + 16777216L >> 25;
        h6 += carry[5];
        h5 -= carry[5] << 25;
        carry[7] = h7 + 16777216L >> 25;
        h8 += carry[7];
        h7 -= carry[7] << 25;
        carry[0] = h0 + 33554432L >> 26;
        h1 += carry[0];
        h0 -= carry[0] << 26;
        carry[2] = h2 + 33554432L >> 26;
        h3 += carry[2];
        h2 -= carry[2] << 26;
        carry[4] = h4 + 33554432L >> 26;
        h5 += carry[4];
        h4 -= carry[4] << 26;
        carry[6] = h6 + 33554432L >> 26;
        h7 += carry[6];
        h6 -= carry[6] << 26;
        carry[8] = h8 + 33554432L >> 26;
        h9 += carry[8];
        h8 -= carry[8] << 26;
        Curve25519.FieldElement h = new Curve25519.FieldElement();
        h.arr[0] = (long)((int)h0);
        h.arr[1] = (long)((int)h1);
        h.arr[2] = (long)((int)h2);
        h.arr[3] = (long)((int)h3);
        h.arr[4] = (long)((int)h4);
        h.arr[5] = (long)((int)h5);
        h.arr[6] = (long)((int)h6);
        h.arr[7] = (long)((int)h7);
        h.arr[8] = (long)((int)h8);
        h.arr[9] = (long)((int)h9);
        return h;
    }

    public static byte[] scalarMult(byte[] in, byte[] base) {
        byte[] e = (byte[])in.clone();
        e[0] &= -8;
        e[31] = (byte)(e[31] & 127);
        e[31] = (byte)(e[31] | 64);
        Curve25519.FieldElement x1 = new Curve25519.FieldElement(base);
        Curve25519.FieldElement x2 = Curve25519.FieldElement.ONE();
        Curve25519.FieldElement x3 = new Curve25519.FieldElement(x1);
        Curve25519.FieldElement z3 = Curve25519.FieldElement.ONE();
        Curve25519.FieldElement z2 = Curve25519.FieldElement.ZERO();
        long swap = 0L;

        for(int pos = 254; pos >= 0; --pos) {
            long b = (long)(e[pos / 8] >> (pos & 7));
            b &= 1L;
            swap ^= b;
            feCSwap(x2, x3, swap);
            feCSwap(z2, z3, swap);
            swap = b;
            Curve25519.FieldElement tmp0 = feSub(x3, z3);
            Curve25519.FieldElement tmp1 = feSub(x2, z2);
            x2 = feAdd(x2, z2);
            z2 = feAdd(x3, z3);
            z3 = feMul(tmp0, x2);
            z2 = feMul(z2, tmp1);
            tmp0 = feSquare(tmp1);
            tmp1 = feSquare(x2);
            x3 = feAdd(z3, z2);
            z2 = feSub(z3, z2);
            x2 = feMul(tmp1, tmp0);
            tmp1 = feSub(tmp1, tmp0);
            z2 = feSquare(z2);
            z3 = feMul121666(tmp1);
            x3 = feSquare(x3);
            tmp0 = feAdd(tmp0, z3);
            z3 = feMul(x1, z2);
            z2 = feMul(tmp1, tmp0);
        }

        feCSwap(x2, x3, swap);
        feCSwap(z2, z3, swap);
        z2 = feInvert(z2);
        x2 = feMul(x2, z2);
        byte[] out = x2.toBytes();
        return out;
    }

    public static byte[] scalarBaseMult(byte[] in) {
        return scalarMult(in, basePoint);
    }

    protected static class FieldElement {
        private long[] arr = new long[10];

        public FieldElement(long[] val) {
            for(int i = 0; i < 10; ++i) {
                this.arr[i] = val[i];
            }

        }

        public FieldElement(Curve25519.FieldElement fe) {
            for(int i = 0; i < this.arr.length; ++i) {
                this.arr[i] = fe.arr[i];
            }

        }

        public FieldElement(byte[] src) {
            long h0 = load4(src, 0);
            long h1 = load3(src, 4) << 6;
            long h2 = load3(src, 7) << 5;
            long h3 = load3(src, 10) << 3;
            long h4 = load3(src, 13) << 2;
            long h5 = load4(src, 16);
            long h6 = load3(src, 20) << 7;
            long h7 = load3(src, 23) << 5;
            long h8 = load3(src, 26) << 4;
            long h9 = load3(src, 29) << 2;
            long[] carry = new long[10];
            carry[9] = h9 + 16777216L >> 25;
            h0 += carry[9] * 19L;
            h9 -= carry[9] << 25;
            carry[1] = h1 + 16777216L >> 25;
            h2 += carry[1];
            h1 -= carry[1] << 25;
            carry[3] = h3 + 16777216L >> 25;
            h4 += carry[3];
            h3 -= carry[3] << 25;
            carry[5] = h5 + 16777216L >> 25;
            h6 += carry[5];
            h5 -= carry[5] << 25;
            carry[7] = h7 + 16777216L >> 25;
            h8 += carry[7];
            h7 -= carry[7] << 25;
            carry[0] = h0 + 33554432L >> 26;
            h1 += carry[0];
            h0 -= carry[0] << 26;
            carry[2] = h2 + 33554432L >> 26;
            h3 += carry[2];
            h2 -= carry[2] << 26;
            carry[4] = h4 + 33554432L >> 26;
            h5 += carry[4];
            h4 -= carry[4] << 26;
            carry[6] = h6 + 33554432L >> 26;
            h7 += carry[6];
            h6 -= carry[6] << 26;
            carry[8] = h8 + 33554432L >> 26;
            h9 += carry[8];
            h8 -= carry[8] << 26;
            this.arr[0] = (long)((int)h0);
            this.arr[1] = (long)((int)h1);
            this.arr[2] = (long)((int)h2);
            this.arr[3] = (long)((int)h3);
            this.arr[4] = (long)((int)h4);
            this.arr[5] = (long)((int)h5);
            this.arr[6] = (long)((int)h6);
            this.arr[7] = (long)((int)h7);
            this.arr[8] = (long)((int)h8);
            this.arr[9] = (long)((int)h9);
        }

        public FieldElement() {
            for(int i = 0; i < this.arr.length; ++i) {
                this.arr[i] = 0L;
            }

        }

        private static long load3(byte[] in, int offset) {
            long r = (long)(255 & in[offset]);
            r |= (long)(255 & in[offset + 1]) << 8;
            r |= (long)(255 & in[offset + 2]) << 16;
            return r;
        }

        private static long load4(byte[] in, int offset) {
            long r = (long)(255 & in[offset]);
            r |= (long)(255 & in[offset + 1]) << 8;
            r |= (long)(255 & in[offset + 2]) << 16;
            r |= (long)(255 & in[offset + 3]) << 24;
            return r;
        }

        public static Curve25519.FieldElement ZERO() {
            return new Curve25519.FieldElement();
        }

        public static Curve25519.FieldElement ONE() {
            Curve25519.FieldElement fe = ZERO();
            fe.arr[0] = 1L;
            return fe;
        }

        public boolean equals(Object obj) {
            Curve25519.FieldElement rhs = (Curve25519.FieldElement)obj;

            for(int i = 0; i < 10; ++i) {
                if(this.arr[i] != rhs.arr[i]) {
                    return false;
                }
            }

            return true;
        }

        public String toString() {
            String s = "[";

            for(int i = 0; i < this.arr.length; ++i) {
                s = s + this.arr[i];
                s = s + " ";
            }

            s = s.substring(0, s.length() - 1);
            s = s + "]";
            return s;
        }

        private byte[] toBytes() {
            byte[] retVal = new byte[32];
            long[] carry = new long[10];
            long q = 19L * this.arr[9] + 16777216L >> 25;
            q = this.arr[0] + q >> 26;
            q = this.arr[1] + q >> 25;
            q = this.arr[2] + q >> 26;
            q = this.arr[3] + q >> 25;
            q = this.arr[4] + q >> 26;
            q = this.arr[5] + q >> 25;
            q = this.arr[6] + q >> 26;
            q = this.arr[7] + q >> 25;
            q = this.arr[8] + q >> 26;
            q = this.arr[9] + q >> 25;
            this.arr[0] += 19L * q;
            carry[0] = this.arr[0] >> 26;
            this.arr[1] += carry[0];
            this.arr[0] -= carry[0] << 26;
            carry[1] = this.arr[1] >> 25;
            this.arr[2] += carry[1];
            this.arr[1] -= carry[1] << 25;
            carry[2] = this.arr[2] >> 26;
            this.arr[3] += carry[2];
            this.arr[2] -= carry[2] << 26;
            carry[3] = this.arr[3] >> 25;
            this.arr[4] += carry[3];
            this.arr[3] -= carry[3] << 25;
            carry[4] = this.arr[4] >> 26;
            this.arr[5] += carry[4];
            this.arr[4] -= carry[4] << 26;
            carry[5] = this.arr[5] >> 25;
            this.arr[6] += carry[5];
            this.arr[5] -= carry[5] << 25;
            carry[6] = this.arr[6] >> 26;
            this.arr[7] += carry[6];
            this.arr[6] -= carry[6] << 26;
            carry[7] = this.arr[7] >> 25;
            this.arr[8] += carry[7];
            this.arr[7] -= carry[7] << 25;
            carry[8] = this.arr[8] >> 26;
            this.arr[9] += carry[8];
            this.arr[8] -= carry[8] << 26;
            carry[9] = this.arr[9] >> 25;
            this.arr[9] -= carry[9] << 25;
            retVal[0] = (byte)((int)(this.arr[0] >> 0));
            retVal[1] = (byte)((int)(this.arr[0] >> 8));
            retVal[2] = (byte)((int)(this.arr[0] >> 16));
            retVal[3] = (byte)((int)(this.arr[0] >> 24 | this.arr[1] << 2));
            retVal[4] = (byte)((int)(this.arr[1] >> 6));
            retVal[5] = (byte)((int)(this.arr[1] >> 14));
            retVal[6] = (byte)((int)(this.arr[1] >> 22 | this.arr[2] << 3));
            retVal[7] = (byte)((int)(this.arr[2] >> 5));
            retVal[8] = (byte)((int)(this.arr[2] >> 13));
            retVal[9] = (byte)((int)(this.arr[2] >> 21 | this.arr[3] << 5));
            retVal[10] = (byte)((int)(this.arr[3] >> 3));
            retVal[11] = (byte)((int)(this.arr[3] >> 11));
            retVal[12] = (byte)((int)(this.arr[3] >> 19 | this.arr[4] << 6));
            retVal[13] = (byte)((int)(this.arr[4] >> 2));
            retVal[14] = (byte)((int)(this.arr[4] >> 10));
            retVal[15] = (byte)((int)(this.arr[4] >> 18));
            retVal[16] = (byte)((int)(this.arr[5] >> 0));
            retVal[17] = (byte)((int)(this.arr[5] >> 8));
            retVal[18] = (byte)((int)(this.arr[5] >> 16));
            retVal[19] = (byte)((int)(this.arr[5] >> 24 | this.arr[6] << 1));
            retVal[20] = (byte)((int)(this.arr[6] >> 7));
            retVal[21] = (byte)((int)(this.arr[6] >> 15));
            retVal[22] = (byte)((int)(this.arr[6] >> 23 | this.arr[7] << 3));
            retVal[23] = (byte)((int)(this.arr[7] >> 5));
            retVal[24] = (byte)((int)(this.arr[7] >> 13));
            retVal[25] = (byte)((int)(this.arr[7] >> 21 | this.arr[8] << 4));
            retVal[26] = (byte)((int)(this.arr[8] >> 4));
            retVal[27] = (byte)((int)(this.arr[8] >> 12));
            retVal[28] = (byte)((int)(this.arr[8] >> 20 | this.arr[9] << 6));
            retVal[29] = (byte)((int)(this.arr[9] >> 2));
            retVal[30] = (byte)((int)(this.arr[9] >> 10));
            retVal[31] = (byte)((int)(this.arr[9] >> 18));
            return retVal;
        }

        public Curve25519.FieldElement square() {
            long f0 = this.arr[0];
            long f1 = this.arr[1];
            long f2 = this.arr[2];
            long f3 = this.arr[3];
            long f4 = this.arr[4];
            long f5 = this.arr[5];
            long f6 = this.arr[6];
            long f7 = this.arr[7];
            long f8 = this.arr[8];
            long f9 = this.arr[9];
            long f0_2 = 2L * f0;
            long f1_2 = 2L * f1;
            long f2_2 = 2L * f2;
            long f3_2 = 2L * f3;
            long f4_2 = 2L * f4;
            long f5_2 = 2L * f5;
            long f6_2 = 2L * f6;
            long f7_2 = 2L * f7;
            long f5_38 = 38L * f5;
            long f6_19 = 19L * f6;
            long f7_38 = 38L * f7;
            long f8_19 = 19L * f8;
            long f9_38 = 38L * f9;
            long f0f0 = Curve25519.int64(f0) * Curve25519.int64(f0);
            long f0f1_2 = Curve25519.int64(f0_2) * Curve25519.int64(f1);
            long f0f2_2 = Curve25519.int64(f0_2) * Curve25519.int64(f2);
            long f0f3_2 = Curve25519.int64(f0_2) * Curve25519.int64(f3);
            long f0f4_2 = Curve25519.int64(f0_2) * Curve25519.int64(f4);
            long f0f5_2 = Curve25519.int64(f0_2) * Curve25519.int64(f5);
            long f0f6_2 = Curve25519.int64(f0_2) * Curve25519.int64(f6);
            long f0f7_2 = Curve25519.int64(f0_2) * Curve25519.int64(f7);
            long f0f8_2 = Curve25519.int64(f0_2) * Curve25519.int64(f8);
            long f0f9_2 = Curve25519.int64(f0_2) * Curve25519.int64(f9);
            long f1f1_2 = Curve25519.int64(f1_2) * Curve25519.int64(f1);
            long f1f2_2 = Curve25519.int64(f1_2) * Curve25519.int64(f2);
            long f1f3_4 = Curve25519.int64(f1_2) * Curve25519.int64(f3_2);
            long f1f4_2 = Curve25519.int64(f1_2) * Curve25519.int64(f4);
            long f1f5_4 = Curve25519.int64(f1_2) * Curve25519.int64(f5_2);
            long f1f6_2 = Curve25519.int64(f1_2) * Curve25519.int64(f6);
            long f1f7_4 = Curve25519.int64(f1_2) * Curve25519.int64(f7_2);
            long f1f8_2 = Curve25519.int64(f1_2) * Curve25519.int64(f8);
            long f1f9_76 = Curve25519.int64(f1_2) * Curve25519.int64(f9_38);
            long f2f2 = Curve25519.int64(f2) * Curve25519.int64(f2);
            long f2f3_2 = Curve25519.int64(f2_2) * Curve25519.int64(f3);
            long f2f4_2 = Curve25519.int64(f2_2) * Curve25519.int64(f4);
            long f2f5_2 = Curve25519.int64(f2_2) * Curve25519.int64(f5);
            long f2f6_2 = Curve25519.int64(f2_2) * Curve25519.int64(f6);
            long f2f7_2 = Curve25519.int64(f2_2) * Curve25519.int64(f7);
            long f2f8_38 = Curve25519.int64(f2_2) * Curve25519.int64(f8_19);
            long f2f9_38 = Curve25519.int64(f2) * Curve25519.int64(f9_38);
            long f3f3_2 = Curve25519.int64(f3_2) * Curve25519.int64(f3);
            long f3f4_2 = Curve25519.int64(f3_2) * Curve25519.int64(f4);
            long f3f5_4 = Curve25519.int64(f3_2) * Curve25519.int64(f5_2);
            long f3f6_2 = Curve25519.int64(f3_2) * Curve25519.int64(f6);
            long f3f7_76 = Curve25519.int64(f3_2) * Curve25519.int64(f7_38);
            long f3f8_38 = Curve25519.int64(f3_2) * Curve25519.int64(f8_19);
            long f3f9_76 = Curve25519.int64(f3_2) * Curve25519.int64(f9_38);
            long f4f4 = Curve25519.int64(f4) * Curve25519.int64(f4);
            long f4f5_2 = Curve25519.int64(f4_2) * Curve25519.int64(f5);
            long f4f6_38 = Curve25519.int64(f4_2) * Curve25519.int64(f6_19);
            long f4f7_38 = Curve25519.int64(f4) * Curve25519.int64(f7_38);
            long f4f8_38 = Curve25519.int64(f4_2) * Curve25519.int64(f8_19);
            long f4f9_38 = Curve25519.int64(f4) * Curve25519.int64(f9_38);
            long f5f5_38 = Curve25519.int64(f5) * Curve25519.int64(f5_38);
            long f5f6_38 = Curve25519.int64(f5_2) * Curve25519.int64(f6_19);
            long f5f7_76 = Curve25519.int64(f5_2) * Curve25519.int64(f7_38);
            long f5f8_38 = Curve25519.int64(f5_2) * Curve25519.int64(f8_19);
            long f5f9_76 = Curve25519.int64(f5_2) * Curve25519.int64(f9_38);
            long f6f6_19 = Curve25519.int64(f6) * Curve25519.int64(f6_19);
            long f6f7_38 = Curve25519.int64(f6) * Curve25519.int64(f7_38);
            long f6f8_38 = Curve25519.int64(f6_2) * Curve25519.int64(f8_19);
            long f6f9_38 = Curve25519.int64(f6) * Curve25519.int64(f9_38);
            long f7f7_38 = Curve25519.int64(f7) * Curve25519.int64(f7_38);
            long f7f8_38 = Curve25519.int64(f7_2) * Curve25519.int64(f8_19);
            long f7f9_76 = Curve25519.int64(f7_2) * Curve25519.int64(f9_38);
            long f8f8_19 = Curve25519.int64(f8) * Curve25519.int64(f8_19);
            long f8f9_38 = Curve25519.int64(f8) * Curve25519.int64(f9_38);
            long f9f9_38 = Curve25519.int64(f9) * Curve25519.int64(f9_38);
            long h0 = f0f0 + f1f9_76 + f2f8_38 + f3f7_76 + f4f6_38 + f5f5_38;
            long h1 = f0f1_2 + f2f9_38 + f3f8_38 + f4f7_38 + f5f6_38;
            long h2 = f0f2_2 + f1f1_2 + f3f9_76 + f4f8_38 + f5f7_76 + f6f6_19;
            long h3 = f0f3_2 + f1f2_2 + f4f9_38 + f5f8_38 + f6f7_38;
            long h4 = f0f4_2 + f1f3_4 + f2f2 + f5f9_76 + f6f8_38 + f7f7_38;
            long h5 = f0f5_2 + f1f4_2 + f2f3_2 + f6f9_38 + f7f8_38;
            long h6 = f0f6_2 + f1f5_4 + f2f4_2 + f3f3_2 + f7f9_76 + f8f8_19;
            long h7 = f0f7_2 + f1f6_2 + f2f5_2 + f3f4_2 + f8f9_38;
            long h8 = f0f8_2 + f1f7_4 + f2f6_2 + f3f5_4 + f4f4 + f9f9_38;
            long h9 = f0f9_2 + f1f8_2 + f2f7_2 + f3f6_2 + f4f5_2;
            long[] carry = new long[10];
            carry[0] = h0 + 33554432L >> 26;
            h1 += carry[0];
            h0 -= carry[0] << 26;
            carry[4] = h4 + 33554432L >> 26;
            h5 += carry[4];
            h4 -= carry[4] << 26;
            carry[1] = h1 + 16777216L >> 25;
            h2 += carry[1];
            h1 -= carry[1] << 25;
            carry[5] = h5 + 16777216L >> 25;
            h6 += carry[5];
            h5 -= carry[5] << 25;
            carry[2] = h2 + 33554432L >> 26;
            h3 += carry[2];
            h2 -= carry[2] << 26;
            carry[6] = h6 + 33554432L >> 26;
            h7 += carry[6];
            h6 -= carry[6] << 26;
            carry[3] = h3 + 16777216L >> 25;
            h4 += carry[3];
            h3 -= carry[3] << 25;
            carry[7] = h7 + 16777216L >> 25;
            h8 += carry[7];
            h7 -= carry[7] << 25;
            carry[4] = h4 + 33554432L >> 26;
            h5 += carry[4];
            h4 -= carry[4] << 26;
            carry[8] = h8 + 33554432L >> 26;
            h9 += carry[8];
            h8 -= carry[8] << 26;
            carry[9] = h9 + 16777216L >> 25;
            h0 += carry[9] * 19L;
            h9 -= carry[9] << 25;
            carry[0] = h0 + 33554432L >> 26;
            h1 += carry[0];
            h0 -= carry[0] << 26;
            Curve25519.FieldElement h = new Curve25519.FieldElement();
            h.arr[0] = (long)((int)h0);
            h.arr[1] = (long)((int)h1);
            h.arr[2] = (long)((int)h2);
            h.arr[3] = (long)((int)h3);
            h.arr[4] = (long)((int)h4);
            h.arr[5] = (long)((int)h5);
            h.arr[6] = (long)((int)h6);
            h.arr[7] = (long)((int)h7);
            h.arr[8] = (long)((int)h8);
            h.arr[9] = (long)((int)h9);
            return h;
        }
    }
}

class Salsa {
    public static byte[] SIGMA = new byte[]{101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107};
    private static int rounds = 20;

    public Salsa() {
    }

    private static long mask(byte x) {
        return 255L & (long)x;
    }

    public static byte[] core(byte[] in, byte[] k, byte[] c) {
        byte[] out = new byte[64];
        long mask = 4294967295L;
        long j0 = mask & (mask(c[0]) | mask(c[1]) << 8 | mask(c[2]) << 16 | mask(c[3]) << 24);
        long j1 = mask & (mask(k[0]) | mask(k[1]) << 8 | mask(k[2]) << 16 | mask(k[3]) << 24);
        long j2 = mask & (mask(k[4]) | mask(k[5]) << 8 | mask(k[6]) << 16 | mask(k[7]) << 24);
        long j3 = mask & (mask(k[8]) | mask(k[9]) << 8 | mask(k[10]) << 16 | mask(k[11]) << 24);
        long j4 = mask & (mask(k[12]) | mask(k[13]) << 8 | mask(k[14]) << 16 | mask(k[15]) << 24);
        long j5 = mask & (mask(c[4]) | mask(c[5]) << 8 | mask(c[6]) << 16 | mask(c[7]) << 24);
        long j6 = mask & (mask(in[0]) | mask(in[1]) << 8 | mask(in[2]) << 16 | mask(in[3]) << 24);
        long j7 = mask & (mask(in[4]) | mask(in[5]) << 8 | mask(in[6]) << 16 | mask(in[7]) << 24);
        long j8 = mask & (mask(in[8]) | mask(in[9]) << 8 | mask(in[10]) << 16 | mask(in[11]) << 24);
        long j9 = mask & (mask(in[12]) | mask(in[13]) << 8 | mask(in[14]) << 16 | mask(in[15]) << 24);
        long j10 = mask & (mask(c[8]) | mask(c[9]) << 8 | mask(c[10]) << 16 | mask(c[11]) << 24);
        long j11 = mask & (mask(k[16]) | mask(k[17]) << 8 | mask(k[18]) << 16 | mask(k[19]) << 24);
        long j12 = mask & (mask(k[20]) | mask(k[21]) << 8 | mask(k[22]) << 16 | mask(k[23]) << 24);
        long j13 = mask & (mask(k[24]) | mask(k[25]) << 8 | mask(k[26]) << 16 | mask(k[27]) << 24);
        long j14 = mask & (mask(k[28]) | mask(k[29]) << 8 | mask(k[30]) << 16 | mask(k[31]) << 24);
        long j15 = mask & (mask(c[12]) | mask(c[13]) << 8 | mask(c[14]) << 16 | mask(c[15]) << 24);
        long x0 = j0;
        long x1 = j1;
        long x2 = j2;
        long x3 = j3;
        long x4 = j4;
        long x5 = j5;
        long x6 = j6;
        long x7 = j7;
        long x8 = j8;
        long x9 = j9;
        long x10 = j10;
        long x11 = j11;
        long x12 = j12;
        long x13 = j13;
        long x14 = j14;
        long x15 = j15;

        for(int i = 0; i < rounds; i += 2) {
            long u = mask & x0 + x12;
            x4 ^= mask & (u << 7 | u >>> 25);
            u = mask & x4 + x0;
            x8 ^= mask & (u << 9 | u >>> 23);
            u = mask & x8 + x4;
            x12 ^= mask & (u << 13 | u >>> 19);
            u = mask & x12 + x8;
            x0 ^= mask & (u << 18 | u >>> 14);
            u = mask & x5 + x1;
            x9 ^= mask & (u << 7 | u >>> 25);
            u = mask & x9 + x5;
            x13 ^= mask & (u << 9 | u >>> 23);
            u = mask & x13 + x9;
            x1 ^= mask & (u << 13 | u >>> 19);
            u = mask & x1 + x13;
            x5 ^= mask & (u << 18 | u >>> 14);
            u = mask & x10 + x6;
            x14 ^= mask & (u << 7 | u >>> 25);
            u = mask & x14 + x10;
            x2 ^= mask & (u << 9 | u >>> 23);
            u = mask & x2 + x14;
            x6 ^= mask & (u << 13 | u >>> 19);
            u = mask & x6 + x2;
            x10 ^= mask & (u << 18 | u >>> 14);
            u = mask & x15 + x11;
            x3 ^= mask & (u << 7 | u >>> 25);
            u = mask & x3 + x15;
            x7 ^= mask & (u << 9 | u >>> 23);
            u = mask & x7 + x3;
            x11 ^= mask & (u << 13 | u >>> 19);
            u = mask & x11 + x7;
            x15 ^= mask & (u << 18 | u >>> 14);
            u = mask & x0 + x3;
            x1 ^= mask & (u << 7 | u >>> 25);
            u = mask & x1 + x0;
            x2 ^= mask & (u << 9 | u >>> 23);
            u = mask & x2 + x1;
            x3 ^= mask & (u << 13 | u >>> 19);
            u = mask & x3 + x2;
            x0 ^= mask & (u << 18 | u >>> 14);
            u = mask & x5 + x4;
            x6 ^= mask & (u << 7 | u >>> 25);
            u = mask & x6 + x5;
            x7 ^= mask & (u << 9 | u >>> 23);
            u = mask & x7 + x6;
            x4 ^= mask & (u << 13 | u >>> 19);
            u = mask & x4 + x7;
            x5 ^= mask & (u << 18 | u >>> 14);
            u = mask & x10 + x9;
            x11 ^= mask & (u << 7 | u >>> 25);
            u = mask & x11 + x10;
            x8 ^= mask & (u << 9 | u >>> 23);
            u = mask & x8 + x11;
            x9 ^= mask & (u << 13 | u >>> 19);
            u = mask & x9 + x8;
            x10 ^= mask & (u << 18 | u >>> 14);
            u = mask & x15 + x14;
            x12 ^= mask & (u << 7 | u >>> 25);
            u = mask & x12 + x15;
            x13 ^= mask & (u << 9 | u >>> 23);
            u = mask & x13 + x12;
            x14 ^= mask & (u << 13 | u >>> 19);
            u = mask & x14 + x13;
            x15 ^= mask & (u << 18 | u >>> 14);
        }

        x0 += j0;
        x1 += j1;
        x2 += j2;
        x3 += j3;
        x4 += j4;
        x5 += j5;
        x6 += j6;
        x7 += j7;
        x8 += j8;
        x9 += j9;
        x10 += j10;
        x11 += j11;
        x12 += j12;
        x13 += j13;
        x14 += j14;
        x15 += j15;
        x0 &= mask;
        x1 &= mask;
        x2 &= mask;
        x3 &= mask;
        x4 &= mask;
        x5 &= mask;
        x6 &= mask;
        x7 &= mask;
        x8 &= mask;
        x9 &= mask;
        x10 &= mask;
        x11 &= mask;
        x12 &= mask;
        x13 &= mask;
        x14 &= mask;
        x15 &= mask;
        out[0] = (byte)((int)x0);
        out[1] = (byte)((int)(x0 >> 8));
        out[2] = (byte)((int)(x0 >> 16));
        out[3] = (byte)((int)(x0 >> 24));
        out[4] = (byte)((int)x1);
        out[5] = (byte)((int)(x1 >> 8));
        out[6] = (byte)((int)(x1 >> 16));
        out[7] = (byte)((int)(x1 >> 24));
        out[8] = (byte)((int)x2);
        out[9] = (byte)((int)(x2 >> 8));
        out[10] = (byte)((int)(x2 >> 16));
        out[11] = (byte)((int)(x2 >> 24));
        out[12] = (byte)((int)x3);
        out[13] = (byte)((int)(x3 >> 8));
        out[14] = (byte)((int)(x3 >> 16));
        out[15] = (byte)((int)(x3 >> 24));
        out[16] = (byte)((int)x4);
        out[17] = (byte)((int)(x4 >> 8));
        out[18] = (byte)((int)(x4 >> 16));
        out[19] = (byte)((int)(x4 >> 24));
        out[20] = (byte)((int)x5);
        out[21] = (byte)((int)(x5 >> 8));
        out[22] = (byte)((int)(x5 >> 16));
        out[23] = (byte)((int)(x5 >> 24));
        out[24] = (byte)((int)x6);
        out[25] = (byte)((int)(x6 >> 8));
        out[26] = (byte)((int)(x6 >> 16));
        out[27] = (byte)((int)(x6 >> 24));
        out[28] = (byte)((int)x7);
        out[29] = (byte)((int)(x7 >> 8));
        out[30] = (byte)((int)(x7 >> 16));
        out[31] = (byte)((int)(x7 >> 24));
        out[32] = (byte)((int)x8);
        out[33] = (byte)((int)(x8 >> 8));
        out[34] = (byte)((int)(x8 >> 16));
        out[35] = (byte)((int)(x8 >> 24));
        out[36] = (byte)((int)x9);
        out[37] = (byte)((int)(x9 >> 8));
        out[38] = (byte)((int)(x9 >> 16));
        out[39] = (byte)((int)(x9 >> 24));
        out[40] = (byte)((int)x10);
        out[41] = (byte)((int)(x10 >> 8));
        out[42] = (byte)((int)(x10 >> 16));
        out[43] = (byte)((int)(x10 >> 24));
        out[44] = (byte)((int)x11);
        out[45] = (byte)((int)(x11 >> 8));
        out[46] = (byte)((int)(x11 >> 16));
        out[47] = (byte)((int)(x11 >> 24));
        out[48] = (byte)((int)x12);
        out[49] = (byte)((int)(x12 >> 8));
        out[50] = (byte)((int)(x12 >> 16));
        out[51] = (byte)((int)(x12 >> 24));
        out[52] = (byte)((int)x13);
        out[53] = (byte)((int)(x13 >> 8));
        out[54] = (byte)((int)(x13 >> 16));
        out[55] = (byte)((int)(x13 >> 24));
        out[56] = (byte)((int)x14);
        out[57] = (byte)((int)(x14 >> 8));
        out[58] = (byte)((int)(x14 >> 16));
        out[59] = (byte)((int)(x14 >> 24));
        out[60] = (byte)((int)x15);
        out[61] = (byte)((int)(x15 >> 8));
        out[62] = (byte)((int)(x15 >> 16));
        out[63] = (byte)((int)(x15 >> 24));
        return out;
    }

    public static byte[] XORKeyStream(byte[] in, byte[] counter, byte[] key) {
        byte[] out = (byte[])in.clone();
        byte[] counterCopy = (byte[])counter.clone();

        byte[] block;
        int count;
        int i;
        for(count = 0; in.length >= 64; ++count) {
            block = core(counterCopy, key, SIGMA);

            for(i = 0; i < block.length; ++i) {
                byte x = block[i];
                out[i + 64 * count] = (byte)(in[i] ^ x);
            }

            long u = 1L;

            for(i = 8; i < 16; ++i) {
                u += (long)(255 & counterCopy[i]);
                counterCopy[i] = (byte)((int)u);
                u >>= 8;
            }

            byte[] temp = (byte[])in.clone();
            in = new byte[in.length - 64];

            for(i = 0; i < in.length; ++i) {
                in[i] = temp[i + 64];
            }
        }

        if(in.length > 0) {
            block = core(counterCopy, key, SIGMA);

            for(i = 0; i < in.length; ++i) {
                out[i + count * 64] = (byte)(in[i] ^ block[i]);
            }
        }

        return out;
    }

    public static byte[] HSalsa20(byte[] in, byte[] k, byte[] c) {
        long x0 = mask(c[0]) | mask(c[1]) << 8 | mask(c[2]) << 16 | mask(c[3]) << 24;
        long x1 = mask(k[0]) | mask(k[1]) << 8 | mask(k[2]) << 16 | mask(k[3]) << 24;
        long x2 = mask(k[4]) | mask(k[5]) << 8 | mask(k[6]) << 16 | mask(k[7]) << 24;
        long x3 = mask(k[8]) | mask(k[9]) << 8 | mask(k[10]) << 16 | mask(k[11]) << 24;
        long x4 = mask(k[12]) | mask(k[13]) << 8 | mask(k[14]) << 16 | mask(k[15]) << 24;
        long x5 = mask(c[4]) | mask(c[5]) << 8 | mask(c[6]) << 16 | mask(c[7]) << 24;
        long x6 = mask(in[0]) | mask(in[1]) << 8 | mask(in[2]) << 16 | mask(in[3]) << 24;
        long x7 = mask(in[4]) | mask(in[5]) << 8 | mask(in[6]) << 16 | mask(in[7]) << 24;
        long x8 = mask(in[8]) | mask(in[9]) << 8 | mask(in[10]) << 16 | mask(in[11]) << 24;
        long x9 = mask(in[12]) | mask(in[13]) << 8 | mask(in[14]) << 16 | mask(in[15]) << 24;
        long x10 = mask(c[8]) | mask(c[9]) << 8 | mask(c[10]) << 16 | mask(c[11]) << 24;
        long x11 = mask(k[16]) | mask(k[17]) << 8 | mask(k[18]) << 16 | mask(k[19]) << 24;
        long x12 = mask(k[20]) | mask(k[21]) << 8 | mask(k[22]) << 16 | mask(k[23]) << 24;
        long x13 = mask(k[24]) | mask(k[25]) << 8 | mask(k[26]) << 16 | mask(k[27]) << 24;
        long x14 = mask(k[28]) | mask(k[29]) << 8 | mask(k[30]) << 16 | mask(k[31]) << 24;
        long x15 = mask(c[12]) | mask(c[13]) << 8 | mask(c[14]) << 16 | mask(c[15]) << 24;
        long mask = 4294967295L;

        for(int i = 0; i < 20; i += 2) {
            long u = mask & x0 + x12;
            x4 ^= mask & (u << 7 | u >>> 25);
            u = mask & x4 + x0;
            x8 ^= mask & (u << 9 | u >>> 23);
            u = mask & x8 + x4;
            x12 ^= mask & (u << 13 | u >>> 19);
            u = mask & x12 + x8;
            x0 ^= mask & (u << 18 | u >>> 14);
            u = mask & x5 + x1;
            x9 ^= mask & (u << 7 | u >>> 25);
            u = mask & x9 + x5;
            x13 ^= mask & (u << 9 | u >>> 23);
            u = mask & x13 + x9;
            x1 ^= mask & (u << 13 | u >>> 19);
            u = mask & x1 + x13;
            x5 ^= mask & (u << 18 | u >>> 14);
            u = mask & x10 + x6;
            x14 ^= mask & (u << 7 | u >>> 25);
            u = mask & x14 + x10;
            x2 ^= mask & (u << 9 | u >>> 23);
            u = mask & x2 + x14;
            x6 ^= mask & (u << 13 | u >>> 19);
            u = mask & x6 + x2;
            x10 ^= mask & (u << 18 | u >>> 14);
            u = mask & x15 + x11;
            x3 ^= mask & (u << 7 | u >>> 25);
            u = mask & x3 + x15;
            x7 ^= mask & (u << 9 | u >>> 23);
            u = mask & x7 + x3;
            x11 ^= mask & (u << 13 | u >>> 19);
            u = mask & x11 + x7;
            x15 ^= mask & (u << 18 | u >>> 14);
            u = mask & x0 + x3;
            x1 ^= mask & (u << 7 | u >>> 25);
            u = mask & x1 + x0;
            x2 ^= mask & (u << 9 | u >>> 23);
            u = mask & x2 + x1;
            x3 ^= mask & (u << 13 | u >>> 19);
            u = mask & x3 + x2;
            x0 ^= mask & (u << 18 | u >>> 14);
            u = mask & x5 + x4;
            x6 ^= mask & (u << 7 | u >>> 25);
            u = mask & x6 + x5;
            x7 ^= mask & (u << 9 | u >>> 23);
            u = mask & x7 + x6;
            x4 ^= mask & (u << 13 | u >>> 19);
            u = mask & x4 + x7;
            x5 ^= mask & (u << 18 | u >>> 14);
            u = mask & x10 + x9;
            x11 ^= mask & (u << 7 | u >>> 25);
            u = mask & x11 + x10;
            x8 ^= mask & (u << 9 | u >>> 23);
            u = mask & x8 + x11;
            x9 ^= mask & (u << 13 | u >>> 19);
            u = mask & x9 + x8;
            x10 ^= mask & (u << 18 | u >>> 14);
            u = mask & x15 + x14;
            x12 ^= mask & (u << 7 | u >>> 25);
            u = mask & x12 + x15;
            x13 ^= mask & (u << 9 | u >>> 23);
            u = mask & x13 + x12;
            x14 ^= mask & (u << 13 | u >>> 19);
            u = mask & x14 + x13;
            x15 ^= mask & (u << 18 | u >>> 14);
        }

        byte[] out = new byte[]{(byte)((int)x0), (byte)((int)(x0 >> 8)), (byte)((int)(x0 >> 16)), (byte)((int)(x0 >> 24)), (byte)((int)x5), (byte)((int)(x5 >> 8)), (byte)((int)(x5 >> 16)), (byte)((int)(x5 >> 24)), (byte)((int)x10), (byte)((int)(x10 >> 8)), (byte)((int)(x10 >> 16)), (byte)((int)(x10 >> 24)), (byte)((int)x15), (byte)((int)(x15 >> 8)), (byte)((int)(x15 >> 16)), (byte)((int)(x15 >> 24)), (byte)((int)x6), (byte)((int)(x6 >> 8)), (byte)((int)(x6 >> 16)), (byte)((int)(x6 >> 24)), (byte)((int)x7), (byte)((int)(x7 >> 8)), (byte)((int)(x7 >> 16)), (byte)((int)(x7 >> 24)), (byte)((int)x8), (byte)((int)(x8 >> 8)), (byte)((int)(x8 >> 16)), (byte)((int)(x8 >> 24)), (byte)((int)x9), (byte)((int)(x9 >> 8)), (byte)((int)(x9 >> 16)), (byte)((int)(x9 >> 24))};
        return out;
    }
}

public class TweetNaCl {

    public static final int crypto_auth_hmacsha512256_tweet_BYTES = 32;
    public static final int crypto_auth_hmacsha512256_tweet_KEYBYTES = 32;
    public static final int BOX_PUBLIC_KEY_BYTES = 32;
    public static final int BOX_SECRET_KEY_BYTES = 32;
    public static final int BOX_SHARED_KEY_BYTES = 32;
    public static final int BOX_NONCE_BYTES = 24;
    public static final int BOX_OVERHEAD_BYTES = 16;
    public static final int SIGNATURE_SIZE_BYTES = 64;
    public static final int SIGN_PUBLIC_KEY_BYTES = 32;
    public static final int SIGN_SECRET_KEY_BYTES = 64;
    public static final int SIGN_KEYPAIR_SEED_BYTES = 32;
    public static final int SECRETBOX_KEY_BYTES = 32;
    public static final int SECRETBOX_NONCE_BYTES = 24;
    public static final int SECRETBOX_OVERHEAD_BYTES = 16;
    public static final int HASH_SIZE_BYTES = 64; // SHA-512
    private static final int SECRETBOX_INTERNAL_OVERHEAD_BYTES = 32;

    public static class InvalidSignatureException extends RuntimeException {}
    public static class InvalidCipherTextException extends RuntimeException {}

    public static void crypto_sign_keypair(byte[] pk, byte[] sk, boolean isSeeded)
    {
        byte[] d = new byte[64];
        long[][] /*gf*/ p = new long[4][GF_LEN];
        int i;

        if (!isSeeded)
            randombytes(sk);
        crypto_hash(d, sk, 32);
        d[0] &= 248;
        d[31] &= 127;
        d[31] |= 64;

        scalarbase(p,d, 0);
        pack(pk,p);

        for (i=0;i < 32;++i)sk[32 + i] = pk[i];
    }

    public static int crypto_box_keypair(byte[] y,byte[] x, boolean isSeeded) {
        if (!isSeeded)
            randombytes(x);
        return crypto_scalarmult_base(y,x);
    }

    public static int crypto_scalarmult_base(byte[] q,byte[] n)
    {
        return crypto_scalarmult(q, n, _9);
    }

    public static byte[] crypto_sign(byte[] message, byte[] secretSigningKey) {
        byte[] signedMessage = new byte[message.length + TweetNaCl.SIGNATURE_SIZE_BYTES];
        TweetNaCl.crypto_sign(signedMessage, message, message.length, secretSigningKey);
        return signedMessage;
    }

    public static byte[] crypto_sign_open(byte[] signed, byte[] publicSigningKey) {
        byte[] message = new byte[signed.length];
        int res = TweetNaCl.crypto_sign_open(message, signed, signed.length, publicSigningKey);
        if (res != 0)
            throw new InvalidSignatureException();
        return Arrays.copyOfRange(message, 64, message.length);
    }

    public static byte[] crypto_box(byte[] message, byte[] nonce, byte[] theirPublicBoxingKey, byte[] ourSecretBoxingKey) {
        if (nonce.length != BOX_NONCE_BYTES)
            throw new IllegalStateException("Illegal nonce length: "+nonce.length);
        byte[] cipherText = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        byte[] paddedMessage = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        System.arraycopy(message, 0, paddedMessage, SECRETBOX_INTERNAL_OVERHEAD_BYTES, message.length);
        TweetNaCl.crypto_box(cipherText, paddedMessage, paddedMessage.length, nonce, theirPublicBoxingKey, ourSecretBoxingKey);
        return Arrays.copyOfRange(cipherText, 16, cipherText.length);
    }

    public static byte[] crypto_box(byte[] message, byte[] nonce, byte[] sk) {
        if (nonce.length != BOX_NONCE_BYTES)
            throw new IllegalStateException("Illegal nonce length: "+nonce.length);
        byte[] cipherText = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        byte[] paddedMessage = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + message.length];
        System.arraycopy(message, 0, paddedMessage, SECRETBOX_INTERNAL_OVERHEAD_BYTES, message.length);
        crypto_box_afternm(cipherText, paddedMessage, paddedMessage.length, nonce, sk);
        return Arrays.copyOfRange(cipherText, 16, cipherText.length);
    }

    public static byte[] crypto_box_open(byte[] cipher, byte[] nonce, byte[] theirPublicBoxingKey, byte[] secretBoxingKey) {
        byte[] paddedCipher = new byte[cipher.length + 16];
        System.arraycopy(cipher, 0, paddedCipher, 16, cipher.length);
        byte[] rawText = new byte[paddedCipher.length];
        int res = TweetNaCl.crypto_box_open(rawText, paddedCipher, paddedCipher.length, nonce, theirPublicBoxingKey, secretBoxingKey);
        if (res != 0)
            throw new InvalidCipherTextException();
        return Arrays.copyOfRange(rawText, 32, rawText.length);
    }

    public static byte[] crypto_box_open(byte[] cipher, byte[] nonce, byte[] sk) {
        byte[] paddedCipher = new byte[cipher.length + 16];
        System.arraycopy(cipher, 0, paddedCipher, 16, cipher.length);
        byte[] rawText = new byte[paddedCipher.length];
        crypto_box_open_afternm(rawText, paddedCipher, paddedCipher.length, nonce, sk);
        return Arrays.copyOfRange(rawText, 32, rawText.length);
    }

    public static byte[] secretbox(byte[] mesage, byte[] nonce, byte[] key) {
        byte[] m = new byte[SECRETBOX_INTERNAL_OVERHEAD_BYTES + mesage.length];
        byte[] c = new byte[m.length];
        System.arraycopy(mesage, 0, m, SECRETBOX_INTERNAL_OVERHEAD_BYTES, mesage.length);
        crypto_secretbox(c, m, m.length, nonce, key);
        return Arrays.copyOfRange(c, SECRETBOX_OVERHEAD_BYTES, c.length);
    }

    public static byte[] secretbox_open(byte[] cipher, byte[] nonce, byte[] key) {
        byte[] c = new byte[SECRETBOX_OVERHEAD_BYTES + cipher.length];
        byte[] m = new byte[c.length];
        System.arraycopy(cipher, 0, c, SECRETBOX_OVERHEAD_BYTES, cipher.length);
        if (c.length < 32) throw new IllegalStateException("Cipher too small!");
        if (crypto_secretbox_open(m, c, c.length, nonce, key) != 0) throw new IllegalStateException("Invalid encryption!");
        return Arrays.copyOfRange(m, SECRETBOX_INTERNAL_OVERHEAD_BYTES, m.length);
    }

    private static byte[] _0 = new byte[16], _9 = new byte[32];
    static {
        _9[0] = 9;
    }
    private static final int GF_LEN = 16;
    private static long[]  gf0 = new long[GF_LEN];
    private static long[] gf1 = new long[GF_LEN]; static{gf1[0] = 1;}
    private static long[]  _121665 = new long[GF_LEN]; static{_121665[0] = 0xDB41; _121665[1] =1;}
    private static long[]  D = new long[]{0x78a3, 0x1359, 0x4dca, 0x75eb, 0xd8ab, 0x4141, 0x0a4d, 0x0070, 0xe898, 0x7779, 0x4079, 0x8cc7, 0xfe73, 0x2b6f, 0x6cee, 0x5203},
            D2 = new long[]{0xf159, 0x26b2, 0x9b94, 0xebd6, 0xb156, 0x8283, 0x149a, 0x00e0, 0xd130, 0xeef3, 0x80f2, 0x198e, 0xfce7, 0x56df, 0xd9dc, 0x2406},
            X = new long[]{0xd51a, 0x8f25, 0x2d60, 0xc956, 0xa7b2, 0x9525, 0xc760, 0x692c, 0xdc5c, 0xfdd6, 0xe231, 0xc0a4, 0x53fe, 0xcd6e, 0x36d3, 0x2169},
            Y = new long[]{0x6658, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666, 0x6666},
            I = new long[]{0xa0b0, 0x4a0e, 0x1b27, 0xc4ee, 0xe478, 0xad2f, 0x1806, 0x2f43, 0xd7a7, 0x3dfb, 0x0099, 0x2b4d, 0xdf0b, 0x4fc1, 0x2480, 0x2b83};

    private static int L32(int x,int c) { return (x << c) | (x >>> (32 - c)); }

    public static int ld32(byte[] x, int off)
    {
        int u = x[off + 3] & 0xff;
        u = (u<<8)|(x[off + 2] & 0xff);
        u = (u<<8)|(x[off + 1] & 0xff);
        return (u<<8)|(x[off + 0] & 0xff);
    }

    private static void st32(byte[] x, int off, int u)
    {
        int i;
        for (i=0;i < 4;++i){ x[off + i] = (byte)u; u >>= 8; }
    }

    private static int vn(byte[] x, int xOff, byte[] y,int n)
    {
        int i,d = 0;
        for (i=0;i < n;++i)d |= 0xff & (x[xOff + i]^y[i]);
        return (1 & ((d - 1) >> 8)) - 1;
    }

    private static int crypto_verify_16(byte[] x, int xOff, byte[] y)
    {
        return vn(x, xOff, y, 16);
    }

    private static int crypto_verify_32(byte[] x,byte[] y)
    {
        return vn(x, 0, y,32);
    }

    private static void core(byte[] out,byte[] in,byte[] k,byte[] c,int h)
    {
        int[] w = new int[16],x = new int[16],y = new int[16],t = new int[4];
        int i,j,m;

        for (i=0;i < 4;++i){
            x[5*i] = ld32(c,4*i);
            x[1+i] = ld32(k,4*i);
            x[6+i] = ld32(in,4*i);
            x[11+i] = ld32(k,16+4*i);
        }

        for (i=0;i < 16;++i)y[i] = x[i];

        for (i=0;i < 20;++i){
            for (j=0;j < 4;++j){
                for (m=0;m < 4;++m)t[m] = x[(5*j+4*m)%16];
                t[1] ^= L32(t[0]+t[3], 7);
                t[2] ^= L32(t[1]+t[0], 9);
                t[3] ^= L32(t[2]+t[1],13);
                t[0] ^= L32(t[3]+t[2],18);
                for (m=0;m < 4;++m)w[4*j+(j+m)%4] = t[m];
            }
            for (m=0;m < 16;++m)x[m] = w[m];
        }

        if (h != 0) {
            for (i=0;i < 16;++i)x[i] += y[i];
            for (i=0;i < 4;++i){
                x[5*i] -= ld32(c,4*i);
                x[6+i] -= ld32(in,4*i);
            }
            for (i=0;i < 4;++i){
                st32(out, 4*i,x[5*i]);
                st32(out, 16+4*i,x[6+i]);
            }
        } else
            for (i=0;i < 16;++i)st32(out, 4 * i,x[i] + y[i]);
    }

    private static int crypto_core_salsa20(byte[] out,byte[] in,byte[] k,byte[] c)
    {
        core(out,in,k,c,0);
        return 0;
    }

    private static int crypto_core_hsalsa20(byte[] out,byte[] in,byte[] k,byte[] c)
    {
        core(out,in,k,c,1);
        return 0;
    }

    private static byte[] sigma = { 101, 120, 112, 97, 110, 100, 32, 51, 50, 45, 98, 121, 116, 101, 32, 107 };

    private static int crypto_stream_salsa20_xor(byte[] c,byte[] m,long b,byte[] n, int nOff, byte[] k)
    {
        byte[] z = new byte[16],x = new byte[64];
        int u,i;
        if (b == 0) return 0;
        for (i=0;i < 16;++i)z[i] = 0;
        for (i=0;i < 8;++i)z[i] = n[nOff + i];
        int cOff = 0;
        int mOff = 0;
        while (b >= 64) {
            crypto_core_salsa20(x,z,k,sigma);
            for (i=0;i < 64; ++i) c[cOff + i] = (byte)((m != null ? m[mOff + i]:0)^ x[i]);
            u = 1;
            for (i = 8;i < 16;++i) {
                u += 0xff & z[i];
                z[i] = (byte)u;
                u >>= 8;
            }
            b -= 64;
            cOff += 64;
            if (m != null) mOff += 64;
        }
        if (b != 0) {
            crypto_core_salsa20(x,z,k,sigma);
            for (i=0;i < b; i++) c[cOff + i] = (byte)((m != null ? m[mOff + i]:0)^ x[i]);
        }
        return 0;
    }

    private static int crypto_stream_salsa20(byte[] c,long d,byte[] n, int nOff, byte[] k)
    {
        return crypto_stream_salsa20_xor(c,null,d,n, nOff, k);
    }

    private static int crypto_stream(byte[] c,long d,byte[] n,byte[] k)
    {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s,n,k,sigma);
        return crypto_stream_salsa20(c, d, n, 16, s);
    }

    private static int crypto_stream_xor(byte[] c,byte[] m,long d,byte[] n,byte[] k)
    {
        byte[] s = new byte[32];
        crypto_core_hsalsa20(s,n,k,sigma);
        return crypto_stream_salsa20_xor(c, m, d, n, 16, s);
    }

    private static void add1305(int[] h,int[] c)
    {
        int j,u = 0;
        for (j=0;j < 17;++j){
            u += h[j] + c[j];
            h[j] = u & 255;
            u >>= 8;
        }
    }

    private static int[] minusp = new int[] {
            5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 252
    } ;

    private static int crypto_onetimeauth(byte[] out, int outOff, byte[] m, int mOff, long n,byte[] k)
    {
        int s,i,j,u;
        int[] x = new int[17],r = new int[17],h = new int[17],c = new int[17],g = new int[17];

        for (j=0;j < 17;++j)
            r[j]= h[j] = 0;
        for (j=0;j < 16;++j)
            r[j] = 0xff & k[j];
        r[3]&=15;
        r[4]&=252;
        r[7]&=15;
        r[8]&=252;
        r[11]&=15;
        r[12]&=252;
        r[15]&=15;

        while (n > 0) {
            for (j=0;j < 17;++j)
                c[j] = 0;
            for (j = 0;(j < 16) && (j < n);++j)
                c[j] = 0xff & m[mOff + j];
            c[j] = 1;
            mOff += j; n -= j;
            add1305(h,c);
            for (i=0;i < 17;++i){
                x[i] = 0;
                for (j=0;j < 17; ++j)
                    x[i] += h[j] * ((j <= i)? r[i - j] : 320 * r[i + 17 - j]);
            }
            for (i=0;i < 17;++i)
                h[i] = x[i];
            u = 0;
            for (j=0;j < 16;++j){
                u += h[j];
                h[j] = u & 255;
                u >>= 8;
            }
            u += h[16]; h[16] = u & 3;
            u = 5 * (u >> 2);
            for (j=0;j < 16;++j){
                u += h[j];
                h[j] = u & 255;
                u >>= 8;
            }
            u += h[16]; h[16] = u;
        }

        for (j=0;j < 17;++j)g[j] = h[j];
        add1305(h,minusp);
        s = -(h[16] >> 7);
        for (j=0;j < 17;++j)h[j] ^= s & (g[j] ^ h[j]);

        for (j=0;j < 16;++j)
            c[j] = 0xff & k[j + 16];
        c[16] = 0;
        add1305(h,c);
        for (j=0;j < 16;++j)out[outOff + j] = (byte)h[j];
        return 0;
    }

    private static int crypto_onetimeauth_verify(byte[] h, int hOff, byte[] m, int mOff, long n,byte[] k)
    {
        byte[] x = new byte[16];
        crypto_onetimeauth(x, 0, m, mOff, n,k);
        return crypto_verify_16(h, hOff, x);
    }

    private static int crypto_secretbox(byte[] c,byte[] m,long d,byte[] n,byte[] k)
    {
        int i;
        if (d < 32) return -1;
        crypto_stream_xor(c,m,d,n,k);
        crypto_onetimeauth(c, 16, c, 32, d - 32, c);
        for (i=0;i < 16;++i)c[i] = 0;
        return 0;
    }

    private static int crypto_secretbox_open(byte[] m,byte[] c,long d,byte[] n,byte[] k)
    {
        int i;
        byte[] x = new byte[32];
        if (d < 32) return -1;
        crypto_stream(x,32,n,k);
        if (crypto_onetimeauth_verify(c, 16,c, 32,d - 32,x) != 0) return -1;
        crypto_stream_xor(m, c, d, n, k);
        for (i=0;i < 32;++i)m[i] = 0;
        return 0;
    }

    private static void set25519(long[] /*gf*/ r, long[] /*gf*/ a)
    {
        int i;
        for (i=0;i < 16;++i)r[i]=a[i];
    }

    private static void car25519(long[] /*gf*/ o, int oOff)
    {
        for (int i=0;i < 16;++i){
            o[oOff + i]+=(1<<16);
            long c=o[oOff + i]>>16;
            o[oOff + (i+1) * (i<15 ? 1:0)] += c - 1 + 37 * (c-1) * (i==15 ? 1 : 0);
            o[oOff + i]-=c<<16;
        }
    }

    private static void sel25519(long[] /*gf*/ p,long[] /*gf*/ q,int b)
    {
        long t,c=~(b-1);
        int i;
        for (i=0;i < 16;++i){
            t= c&(p[i]^q[i]);
            p[i]^=t;
            q[i]^=t;
        }
    }

    private static void pack25519(byte[] o,long[] /*gf*/ n, int nOff)
    {
        int i,j,b;
        long[] /*gf*/ m = new long[GF_LEN],t = new long[GF_LEN];
        for (i=0;i < 16;++i)t[i]=n[nOff+i];
        car25519(t, 0);
        car25519(t, 0);
        car25519(t, 0);
        for (j=0;j < 2;++j){
            m[0]=t[0]-0xffed;
            for(i=1;i<15;i++) {
                m[i]=t[i]-0xffff-((m[i-1]>>16)&1);
                m[i-1]&=0xffff;
            }
            m[15]=t[15]-0x7fff-((m[14]>>16)&1);
            b=(int)((m[15]>>16)&1);
            m[14]&=0xffff;
            sel25519(t,m,1-b);
        }
        for (i=0;i < 16;++i){
            o[2*i]=(byte)t[i];
            o[2*i+1]=(byte)(t[i]>>8);
        }
    }

    private static int neq25519(long[] /*gf*/ a, long[] /*gf*/ b)
    {
        byte[] c = new byte[32],d = new byte[32];
        pack25519(c,a, 0);
        pack25519(d,b, 0);
        return crypto_verify_32(c,d);
    }

    private static byte par25519(long[] /*gf*/ a)
    {
        byte[] d = new byte[32];
        pack25519(d,a, 0);
        return (byte)(d[0]&1);
    }

    private static void unpack25519(long[] /*gf*/ o, byte[] n)
    {
        int i;
        for (i=0;i < 16;++i)
            o[i] = (0xff & n[2*i])+((0xffL & n[2*i+1])<<8);
        o[15]&=0x7fff;
    }

    private static void A(long[] /*gf*/ o,long[] /*gf*/ a,long[] /*gf*/ b)
    {
        int i;
        for (i=0;i < 16;++i)o[i]=a[i]+b[i];
    }

    private static void Z(long[] /*gf*/ o,long[] /*gf*/ a,long[] /*gf*/ b)
    {
        int i;
        for (i=0;i < 16;++i)o[i]=a[i]-b[i];
    }

    private static void M(long[] /*gf*/ o, int oOff, long[] /*gf*/ a, int aOff, long[] /*gf*/ b, int bOff)
    {
        long[] t = new long[31];
        for (int i=0;i < 31;++i)t[i]=0;
        for (int i=0;i < 16; ++i) for(int j=0; j <16;++j)t[i+j]+=a[aOff + i]*b[bOff + j];
        for (int i=0;i < 15;++i)t[i]+=38*t[i+16];
        for (int i=0;i < 16;++i)o[oOff + i]=t[i];
        car25519(o, oOff);
        car25519(o, oOff);
    }

    private static void S(long[] /*gf*/ o,long[] /*gf*/ a)
    {
        M(o, 0, a, 0, a, 0);
    }

    private static void inv25519(long[] /*gf*/ o, int oOff, long[] /*gf*/ i, int iOff)
    {
        long[] /*gf*/ c = new long[GF_LEN];
        int a;
        for (a=0;a < 16;++a)c[a]=i[iOff + a];
        for(a=253;a>=0;a--) {
            S(c,c);
            if(a!=2&&a!=4) M(c, 0, c, 0, i, iOff);
        }
        for (a=0;a < 16;++a)o[oOff + a]=c[a];
    }

    private static void pow2523(long[] /*gf*/ o,long[] /*gf*/ i)
    {
        long[] /*gf*/ c = new long[GF_LEN];
        int a;
        for (a=0;a < 16;++a)c[a]=i[a];
        for(a=250;a>=0;a--) {
            S(c,c);
            if(a!=1) M(c, 0, c, 0, i, 0);
        }
        for (a=0;a < 16;++a)o[a]=c[a];
    }

    private static int crypto_scalarmult(byte[] q,byte[] n,byte[] p)
    {
        byte[] z = new byte[32];
        long[] x = new long[80];
        int r;
        int i;
        long[] /*gf*/ a = new long[GF_LEN],b = new long[GF_LEN],c = new long[GF_LEN],
                d = new long[GF_LEN],e = new long[GF_LEN],f = new long[GF_LEN];
        for (i=0;i < 31;++i)
            z[i] = n[i];
        z[31] = (byte)((n[31]&127)|64);
        z[0] &= 248;
        unpack25519(x,p);
        for (i=0;i < 16;++i){
            b[i]=x[i];
            d[i]=a[i]=c[i]=0;
        }
        a[0]=d[0]=1;

        for(i=254;i>=0;--i) {
            r=( (0xff & z[i>>3]) >> (i&7))&1;
            sel25519(a,b,r);
            sel25519(c,d,r);
            A(e,a,c);
            Z(a,a,c);
            A(c,b,d);
            Z(b,b,d);
            S(d,e);
            S(f,a);
            M(a, 0, c, 0, a, 0);
            M(c, 0, b, 0, e, 0);
            A(e,a,c);
            Z(a,a,c);
            S(b, a);
            Z(c,d,f);
            M(a, 0, c, 0, _121665, 0);
            A(a, a, d);
            M(c, 0, c, 0, a, 0);
            M(a, 0, d, 0, f, 0);
            M(d, 0, b, 0, x, 0);
            S(b,e);
            sel25519(a,b,r);
            sel25519(c,d,r);
        }
        for (i=0;i < 16;++i){
            x[i+16]=a[i];
            x[i+32]=c[i];
            x[i+48]=b[i];
            x[i+64]=d[i];
        }

        inv25519(x, 32,x, 32);

        M(x, 16,x, 16, x, 32);

        pack25519(q,x, 16);
        return 0;
    }

    public static int crypto_box_beforenm(byte[] k,byte[] y,byte[] x)
    {
        byte[] s = new byte[32];
        crypto_scalarmult(s, x, y);
        return crypto_core_hsalsa20(k,_0,s,sigma);
    }

    private static int crypto_box_afternm(byte[] c,byte[] m,long d,byte[] n,byte[] k)
    {
        return crypto_secretbox(c, m, d, n, k);
    }

    private static int crypto_box_open_afternm(byte[] m,byte[] c,long d,byte[] n,byte[] k)
    {
        return crypto_secretbox_open(m, c, d, n, k);
    }

    private static int crypto_box(byte[] c,byte[] m,long d,byte[] nonce, byte[] theirPublicBoxingKey, byte[] ourSecretBoxingKey)
    {
        byte[] k = new byte[32];
        crypto_box_beforenm(k, theirPublicBoxingKey, ourSecretBoxingKey);
        return crypto_box_afternm(c, m, d, nonce, k);
    }

    private static int crypto_box_open(byte[] m,byte[] c,long d,byte[] n,byte[] y,byte[] x)
    {
        byte[] k = new byte[32];
        crypto_box_beforenm(k,y,x);
        return crypto_box_open_afternm(m, c, d, n, k);
    }

    private static int crypto_hash(byte[] out, byte[] m, int n) {
        int[] hh = new int[8], hl = new int[8];
        byte[] x = new byte[256];
        int i, b = n;

        hh[0] = 0x6a09e667;
        hh[1] = 0xbb67ae85;
        hh[2] = 0x3c6ef372;
        hh[3] = 0xa54ff53a;
        hh[4] = 0x510e527f;
        hh[5] = 0x9b05688c;
        hh[6] = 0x1f83d9ab;
        hh[7] = 0x5be0cd19;

        hl[0] = 0xf3bcc908;
        hl[1] = 0x84caa73b;
        hl[2] = 0xfe94f82b;
        hl[3] = 0x5f1d36f1;
        hl[4] = 0xade682d1;
        hl[5] = 0x2b3e6c1f;
        hl[6] = 0xfb41bd6b;
        hl[7] = 0x137e2179;

        crypto_hashblocks_hl(hh, hl, m, n);
        n %= 128;

        for (i = 0; i < n; i++) x[i] = m[b-n+i];
        x[n] = (byte)128;

        n = 256-128*(n<112?1:0);
        x[n-9] = 0;
        jsts64(x, n - 8, (b / 0x20000000), b << 3);
        crypto_hashblocks_hl(hh, hl, x, n);

        for (i = 0; i < 8; i++) jsts64(out, 8 * i, hh[i], hl[i]);

        return 0;
    }

    private static void jsts64(byte[] x, int i, int h, int l) {
        x[i]   = (byte)(h >> 24);
        x[i+1] = (byte)(h >> 16);
        x[i+2] = (byte)(h >>  8);
        x[i+3] = (byte)h;
        x[i+4] = (byte)(l >> 24);
        x[i+5] = (byte)(l >> 16);
        x[i+6] = (byte)(l >>  8);
        x[i+7] = (byte)l;
    }

    private static int[] jsK = new int[]{
            0x428a2f98, 0xd728ae22, 0x71374491, 0x23ef65cd,
            0xb5c0fbcf, 0xec4d3b2f, 0xe9b5dba5, 0x8189dbbc,
            0x3956c25b, 0xf348b538, 0x59f111f1, 0xb605d019,
            0x923f82a4, 0xaf194f9b, 0xab1c5ed5, 0xda6d8118,
            0xd807aa98, 0xa3030242, 0x12835b01, 0x45706fbe,
            0x243185be, 0x4ee4b28c, 0x550c7dc3, 0xd5ffb4e2,
            0x72be5d74, 0xf27b896f, 0x80deb1fe, 0x3b1696b1,
            0x9bdc06a7, 0x25c71235, 0xc19bf174, 0xcf692694,
            0xe49b69c1, 0x9ef14ad2, 0xefbe4786, 0x384f25e3,
            0x0fc19dc6, 0x8b8cd5b5, 0x240ca1cc, 0x77ac9c65,
            0x2de92c6f, 0x592b0275, 0x4a7484aa, 0x6ea6e483,
            0x5cb0a9dc, 0xbd41fbd4, 0x76f988da, 0x831153b5,
            0x983e5152, 0xee66dfab, 0xa831c66d, 0x2db43210,
            0xb00327c8, 0x98fb213f, 0xbf597fc7, 0xbeef0ee4,
            0xc6e00bf3, 0x3da88fc2, 0xd5a79147, 0x930aa725,
            0x06ca6351, 0xe003826f, 0x14292967, 0x0a0e6e70,
            0x27b70a85, 0x46d22ffc, 0x2e1b2138, 0x5c26c926,
            0x4d2c6dfc, 0x5ac42aed, 0x53380d13, 0x9d95b3df,
            0x650a7354, 0x8baf63de, 0x766a0abb, 0x3c77b2a8,
            0x81c2c92e, 0x47edaee6, 0x92722c85, 0x1482353b,
            0xa2bfe8a1, 0x4cf10364, 0xa81a664b, 0xbc423001,
            0xc24b8b70, 0xd0f89791, 0xc76c51a3, 0x0654be30,
            0xd192e819, 0xd6ef5218, 0xd6990624, 0x5565a910,
            0xf40e3585, 0x5771202a, 0x106aa070, 0x32bbd1b8,
            0x19a4c116, 0xb8d2d0c8, 0x1e376c08, 0x5141ab53,
            0x2748774c, 0xdf8eeb99, 0x34b0bcb5, 0xe19b48a8,
            0x391c0cb3, 0xc5c95a63, 0x4ed8aa4a, 0xe3418acb,
            0x5b9cca4f, 0x7763e373, 0x682e6ff3, 0xd6b2b8a3,
            0x748f82ee, 0x5defb2fc, 0x78a5636f, 0x43172f60,
            0x84c87814, 0xa1f0ab72, 0x8cc70208, 0x1a6439ec,
            0x90befffa, 0x23631e28, 0xa4506ceb, 0xde82bde9,
            0xbef9a3f7, 0xb2c67915, 0xc67178f2, 0xe372532b,
            0xca273ece, 0xea26619c, 0xd186b8c7, 0x21c0c207,
            0xeada7dd6, 0xcde0eb1e, 0xf57d4f7f, 0xee6ed178,
            0x06f067aa, 0x72176fba, 0x0a637dc5, 0xa2c898a6,
            0x113f9804, 0xbef90dae, 0x1b710b35, 0x131c471b,
            0x28db77f5, 0x23047d84, 0x32caab7b, 0x40c72493,
            0x3c9ebe0a, 0x15c9bebc, 0x431d67c4, 0x9c100d4c,
            0x4cc5d4be, 0xcb3e42b6, 0x597f299c, 0xfc657e2a,
            0x5fcb6fab, 0x3ad6faec, 0x6c44198c, 0x4a475817
    };

    private static int crypto_hashblocks_hl(int[] hh, int[] hl, byte[] m, int n) {
        int[] wh = new int[16], wl = new int[16];
        int bh0, bh1, bh2, bh3, bh4, bh5, bh6, bh7,
                bl0, bl1, bl2, bl3, bl4, bl5, bl6, bl7,
                th, tl, i, j, h, l, a, b, c, d;

        int ah0 = hh[0],
                ah1 = hh[1],
                ah2 = hh[2],
                ah3 = hh[3],
                ah4 = hh[4],
                ah5 = hh[5],
                ah6 = hh[6],
                ah7 = hh[7],

                al0 = hl[0],
                al1 = hl[1],
                al2 = hl[2],
                al3 = hl[3],
                al4 = hl[4],
                al5 = hl[5],
                al6 = hl[6],
                al7 = hl[7];

        int pos = 0;
        while (n >= 128) {
            for (i = 0; i < 16; i++) {
                j = 8 * i + pos;
                wh[i] = ((m[j+0] & 0xff) << 24) | ((m[j+1] & 0xff) << 16) | ((m[j+2] & 0xff) << 8) | (m[j+3] & 0xff);
                wl[i] = ((m[j+4] & 0xff) << 24) | ((m[j+5] & 0xff) << 16) | ((m[j+6] & 0xff) << 8) | (m[j+7] & 0xff);
            }
            for (i = 0; i < 80; i++) {
                bh0 = ah0;
                bh1 = ah1;
                bh2 = ah2;
                bh3 = ah3;
                bh4 = ah4;
                bh5 = ah5;
                bh6 = ah6;
                bh7 = ah7;

                bl0 = al0;
                bl1 = al1;
                bl2 = al2;
                bl3 = al3;
                bl4 = al4;
                bl5 = al5;
                bl6 = al6;
                bl7 = al7;

                // add
                h = ah7;
                l = al7;

                a = l & 0xffff; b = l >>> 16;
                c = h & 0xffff; d = h >>> 16;

                // Sigma1
                h = ((ah4 >>> 14) | (al4 << (32-14))) ^ ((ah4 >>> 18) | (al4 << (32-18))) ^ ((al4 >>> (41-32)) | (ah4 << (32-(41-32))));
                l = ((al4 >>> 14) | (ah4 << (32-14))) ^ ((al4 >>> 18) | (ah4 << (32-18))) ^ ((ah4 >>> (41-32)) | (al4 << (32-(41-32))));

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                // Ch
                h = (ah4 & ah5) ^ (~ah4 & ah6);
                l = (al4 & al5) ^ (~al4 & al6);

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                // K
                h = jsK[i*2];
                l = jsK[i*2+1];

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                // w
                h = wh[i%16];
                l = wl[i%16];

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                b += a >>> 16;
                c += b >>> 16;
                d += c >>> 16;

                th = c & 0xffff | d << 16;
                tl = a & 0xffff | b << 16;

                // add
                h = th;
                l = tl;

                a = l & 0xffff; b = l >>> 16;
                c = h & 0xffff; d = h >>> 16;

                // Sigma0
                h = ((ah0 >>> 28) | (al0 << (32-28))) ^ ((al0 >>> (34-32)) | (ah0 << (32-(34-32)))) ^ ((al0 >>> (39-32)) | (ah0 << (32-(39-32))));
                l = ((al0 >>> 28) | (ah0 << (32-28))) ^ ((ah0 >>> (34-32)) | (al0 << (32-(34-32)))) ^ ((ah0 >>> (39-32)) | (al0 << (32-(39-32))));

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                // Maj
                h = (ah0 & ah1) ^ (ah0 & ah2) ^ (ah1 & ah2);
                l = (al0 & al1) ^ (al0 & al2) ^ (al1 & al2);

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                b += a >>> 16;
                c += b >>> 16;
                d += c >>> 16;

                bh7 = (c & 0xffff) | (d << 16);
                bl7 = (a & 0xffff) | (b << 16);

                // add
                h = bh3;
                l = bl3;

                a = l & 0xffff; b = l >>> 16;
                c = h & 0xffff; d = h >>> 16;

                h = th;
                l = tl;

                a += l & 0xffff; b += l >>> 16;
                c += h & 0xffff; d += h >>> 16;

                b += a >>> 16;
                c += b >>> 16;
                d += c >>> 16;

                bh3 = (c & 0xffff) | (d << 16);
                bl3 = (a & 0xffff) | (b << 16);

                ah1 = bh0;
                ah2 = bh1;
                ah3 = bh2;
                ah4 = bh3;
                ah5 = bh4;
                ah6 = bh5;
                ah7 = bh6;
                ah0 = bh7;

                al1 = bl0;
                al2 = bl1;
                al3 = bl2;
                al4 = bl3;
                al5 = bl4;
                al6 = bl5;
                al7 = bl6;
                al0 = bl7;

                if (i%16 == 15) {
                    for (j = 0; j < 16; j++) {
                        // add
                        h = wh[j];
                        l = wl[j];

                        a = l & 0xffff; b = l >>> 16;
                        c = h & 0xffff; d = h >>> 16;

                        h = wh[(j+9)%16];
                        l = wl[(j+9)%16];

                        a += l & 0xffff; b += l >>> 16;
                        c += h & 0xffff; d += h >>> 16;

                        // sigma0
                        th = wh[(j+1)%16];
                        tl = wl[(j+1)%16];
                        h = ((th >>> 1) | (tl << (32-1))) ^ ((th >>> 8) | (tl << (32-8))) ^ (th >>> 7);
                        l = ((tl >>> 1) | (th << (32-1))) ^ ((tl >>> 8) | (th << (32-8))) ^ ((tl >>> 7) | (th << (32-7)));

                        a += l & 0xffff; b += l >>> 16;
                        c += h & 0xffff; d += h >>> 16;

                        // sigma1
                        th = wh[(j+14)%16];
                        tl = wl[(j+14)%16];
                        h = ((th >>> 19) | (tl << (32-19))) ^ ((tl >>> (61-32)) | (th << (32-(61-32)))) ^ (th >>> 6);
                        l = ((tl >>> 19) | (th << (32-19))) ^ ((th >>> (61-32)) | (tl << (32-(61-32)))) ^ ((tl >>> 6) | (th << (32-6)));

                        a += l & 0xffff; b += l >>> 16;
                        c += h & 0xffff; d += h >>> 16;

                        b += a >>> 16;
                        c += b >>> 16;
                        d += c >>> 16;

                        wh[j] = (c & 0xffff) | (d << 16);
                        wl[j] = (a & 0xffff) | (b << 16);
                    }
                }
            }

            // add
            h = ah0;
            l = al0;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[0];
            l = hl[0];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[0] = ah0 = (c & 0xffff) | (d << 16);
            hl[0] = al0 = (a & 0xffff) | (b << 16);

            h = ah1;
            l = al1;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[1];
            l = hl[1];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[1] = ah1 = (c & 0xffff) | (d << 16);
            hl[1] = al1 = (a & 0xffff) | (b << 16);

            h = ah2;
            l = al2;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[2];
            l = hl[2];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[2] = ah2 = (c & 0xffff) | (d << 16);
            hl[2] = al2 = (a & 0xffff) | (b << 16);

            h = ah3;
            l = al3;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[3];
            l = hl[3];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[3] = ah3 = (c & 0xffff) | (d << 16);
            hl[3] = al3 = (a & 0xffff) | (b << 16);

            h = ah4;
            l = al4;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[4];
            l = hl[4];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[4] = ah4 = (c & 0xffff) | (d << 16);
            hl[4] = al4 = (a & 0xffff) | (b << 16);

            h = ah5;
            l = al5;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[5];
            l = hl[5];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[5] = ah5 = (c & 0xffff) | (d << 16);
            hl[5] = al5 = (a & 0xffff) | (b << 16);

            h = ah6;
            l = al6;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[6];
            l = hl[6];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[6] = ah6 = (c & 0xffff) | (d << 16);
            hl[6] = al6 = (a & 0xffff) | (b << 16);

            h = ah7;
            l = al7;

            a = l & 0xffff; b = l >>> 16;
            c = h & 0xffff; d = h >>> 16;

            h = hh[7];
            l = hl[7];

            a += l & 0xffff; b += l >>> 16;
            c += h & 0xffff; d += h >>> 16;

            b += a >>> 16;
            c += b >>> 16;
            d += c >>> 16;

            hh[7] = ah7 = (c & 0xffff) | (d << 16);
            hl[7] = al7 = (a & 0xffff) | (b << 16);

            pos += 128;
            n -= 128;
        }

        return n;
    }

    private static void add(long[][] /*gf*/ p/*[4]*/,long[][] /*gf*/ q/*[4]*/)
    {
        long[] /*gf*/ a=new long[GF_LEN],b=new long[GF_LEN],c=new long[GF_LEN],
                d=new long[GF_LEN],t=new long[GF_LEN],e=new long[GF_LEN],
                f=new long[GF_LEN],g=new long[GF_LEN],h=new long[GF_LEN];

        Z(a, p[1], p[0]);
        Z(t, q[1], q[0]);
        M(a, 0, a, 0, t, 0);
        A(b, p[0], p[1]);
        A(t, q[0], q[1]);
        M(b, 0, b, 0, t, 0);
        M(c, 0, p[3], 0, q[3], 0);
        M(c, 0, c, 0, D2, 0);
        M(d, 0, p[2], 0, q[2], 0);
        A(d, d, d);
        Z(e, b, a);
        Z(f, d, c);
        A(g, d, c);
        A(h, b, a);

        M(p[0], 0, e, 0, f, 0);
        M(p[1], 0, h, 0, g, 0);
        M(p[2], 0, g, 0, f, 0);
        M(p[3], 0, e, 0, h, 0);
    }

    private static void cswap(long[][] /*gf*/ p/*[4]*/,long[][] /*gf*/ q/*[4]*/,byte b)
    {
        int i;
        for(i=0; i < 4; i++)
            sel25519(p[i],q[i],b & 0xff);
    }

    private static void pack(byte[] r,long[][] /*gf*/ p/*[4]*/)
    {
        long[] /*gf*/ tx = new long[GF_LEN], ty = new long[GF_LEN], zi = new long[GF_LEN];
        inv25519(zi, 0, p[2], 0);
        M(tx, 0, p[0], 0, zi, 0);
        M(ty, 0, p[1], 0, zi, 0);
        pack25519(r, ty, 0);
        r[31] ^= par25519(tx) << 7;
    }

    private static void scalarmult(long[][] /*gf*/ p/*[4]*/,long[][] /*gf*/ q/*[4]*/,byte[] s, int sOff)
    {
        int i;
        set25519(p[0], gf0);
        set25519(p[1], gf1);
        set25519(p[2], gf1);
        set25519(p[3], gf0);
        for (i = 255;i >= 0;--i) {
            byte b = (byte)(( (0xff & s[sOff + i/8]) >> (i&7))&1);
            cswap(p,q,b);
            add(q,p);
            add(p,p);
            cswap(p,q,b);
        }
    }

    private static void scalarbase(long[][] /*gf*/ p/*[4]*/,byte[] s,  int sOff)
    {
        long[][] /*gf*/ q = new long[4][16];
        set25519(q[0],X);
        set25519(q[1],Y);
        set25519(q[2],gf1);
        M(q[3], 0, X, 0, Y, 0);
        scalarmult(p,q,s, sOff);
    }

    private static long[] L = {0xed, 0xd3, 0xf5, 0x5c, 0x1a, 0x63, 0x12, 0x58,
            0xd6, 0x9c, 0xf7, 0xa2, 0xde, 0xf9, 0xde, 0x14,
            0, 0, 0, 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0, 0x10};

    private static void modL(byte[] r, int rOff, long[] x/*[64]*/)
    {
        long carry;
        int i,j;
        for (i = 63;i >= 32;--i) {
            carry = 0;
            for (j = i - 32;j < i - 12;++j) {
                x[j] += carry - 16 * x[i] * L[j - (i - 32)];
                carry = (x[j] + 128) >> 8;
                x[j] -= carry << 8;
            }
            x[j] += carry;
            x[i] = 0;
        }
        carry = 0;
        for (j=0;j < 32;++j){
            x[j] += carry - (x[31] >> 4) * L[j];
            carry = x[j] >> 8;
            x[j] &= 255;
        }
        for (j=0;j < 32;++j)x[j] -= carry * L[j];
        for (i=0;i < 32;++i){
            x[i+1] += x[i] >> 8;
            r[rOff + i] = (byte)(x[i] & 255);
        }
    }

    private static void reduce(byte[] r)
    {
        long[] x = new long[64];
        for (int i=0;i < 64; i++) x[i] = 0xff & r[i];
        for (int i=0;i < 64;++i)r[i] = 0;
        modL(r, 0, x);
    }

    private static int crypto_sign(byte[] sm, byte[] m,int n,byte[] sk)
    {
        byte[] d = new byte[64],h = new byte[64],r = new byte[64];
        long[] x = new long[64];
        long[][] /*gf*/ p/*[4]*/ = new long[4][GF_LEN];

        crypto_hash(d, sk, 32);
        d[0] &= 248;
        d[31] &= 127;
        d[31] |= 64;

        //        smlen[0] = n+64;
        for (int i=0;i < n;++i)sm[64 + i] = m[i];
        for (int i=0;i < 32;++i)sm[32 + i] = d[32 + i];
        crypto_hash(r, Arrays.copyOfRange(sm, 32, sm.length), n + 32);
        reduce(r);
        scalarbase(p, r, 0);
        pack(sm, p);

        for (int i=0;i < 32;++i)sm[i+32] = sk[i+32];
        crypto_hash(h, sm, n + 64);
        reduce(h);

        for (int i=0;i < 64;++i) x[i] = 0;
        for (int i=0;i < 32; ++i) x[i] = 0xff & r[i];
        for (int i=0;i < 32; ++i) for(int j=0; j < 32; ++j) x[i+j] += (0xff & h[i]) * (0xff & d[j]);
        modL(sm, 32,x);

        return 0;
    }

    private static int unpackneg(long[][] /*gf*/ r/*[4]*/,byte[] p/*[32]*/)
    {
        long[] /*gf*/ t = new long[GF_LEN], chk = new long[GF_LEN], num = new long[GF_LEN], den = new long[GF_LEN],
                den2 = new long[GF_LEN], den4 = new long[GF_LEN], den6 = new long[GF_LEN];
        set25519(r[2],gf1);
        unpack25519(r[1],p);
        S(num, r[1]);
        M(den, 0, num, 0, D, 0);
        Z(num,num,r[2]);
        A(den,r[2],den);

        S(den2,den);
        S(den4,den2);
        M(den6, 0, den4, 0, den2, 0);
        M(t, 0, den6, 0, num, 0);
        M(t, 0, t, 0, den, 0);

        pow2523(t, t);
        M(t, 0, t, 0, num, 0);
        M(t, 0, t, 0, den, 0);
        M(t, 0, t, 0, den, 0);
        M(r[0], 0, t, 0, den, 0);

        S(chk,r[0]);
        M(chk, 0, chk, 0, den, 0);
        if (neq25519(chk, num) != 0) M(r[0], 0, r[0], 0, I, 0);

        S(chk, r[0]);
        M(chk, 0, chk, 0, den, 0);
        if (neq25519(chk, num) != 0) return -1;

        if (par25519(r[0]) == ( (0xff & p[31]) >> 7)) Z(r[0],gf0,r[0]);

        M(r[3], 0, r[0], 0, r[1], 0);
        return 0;
    }

    private static int crypto_sign_open(byte[] m, byte[] sm, int n, byte[] pk)
    {
        int i;
        byte[] t = new byte[32],h = new byte[64];
        long[][] /*gf*/ p = new long[4][GF_LEN],q = new long[4][GF_LEN];

        //        mlen[0] = -1;
        if (n < 64) return -1;

        if (unpackneg(q,pk) != 0) return -1;

        for (i=0;i < n;++i) m[i] = sm[i];
        for (i=0;i < 32;++i) m[i+32] = pk[i];
        crypto_hash(h, m, n);
        reduce(h);
        scalarmult(p, q, h, 0);

        scalarbase(q, sm, 32);
        add(p, q);
        pack(t, p);

        n -= 64;
        if (crypto_verify_32(sm, t) != 0) {
            for (i=0;i < n;++i)m[i] = 0;
            return -1;
        }

        for (i=0;i < n;++i)m[64 + i] = sm[i + 64];
        //        mlen[0] = n;
        return 0;
    }

    private static SecureRandom getStrongCSPRNG() {
        try {
            return SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void randombytes(byte[] b) {
        byte[] r = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(r);
        System.arraycopy(r, 0, b, 0, 32);
    }
}