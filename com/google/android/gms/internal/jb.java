// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class jb
{
    static final int HA;
    public static final int[] HB;
    public static final long[] HC;
    public static final float[] HD;
    public static final double[] HE;
    public static final boolean[] HF;
    public static final String[] HG;
    public static final byte[][] HH;
    public static final byte[] HI;
    static final int Hx;
    static final int Hy;
    static final int Hz;
    
    static {
        Hx = g(1, 3);
        Hy = g(1, 4);
        Hz = g(2, 0);
        HA = g(3, 2);
        HB = new int[0];
        HC = new long[0];
        HD = new float[0];
        HE = new double[0];
        HF = new boolean[0];
        HG = new String[0];
        HH = new byte[0][];
        HI = new byte[0];
    }
    
    public static boolean a(final iw iw, final int n) throws IOException {
        return iw.bJ(n);
    }
    
    static int bS(final int n) {
        return n & 0x7;
    }
    
    public static int bT(final int n) {
        return n >>> 3;
    }
    
    static int g(final int n, final int n2) {
        return n << 3 | n2;
    }
}
