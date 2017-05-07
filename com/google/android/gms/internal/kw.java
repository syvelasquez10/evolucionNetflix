// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class kw
{
    public static final int[] aea;
    public static final long[] aeb;
    public static final float[] aec;
    public static final double[] aed;
    public static final boolean[] aee;
    public static final String[] aef;
    public static final byte[][] aeg;
    public static final byte[] aeh;
    
    static {
        aea = new int[0];
        aeb = new long[0];
        aec = new float[0];
        aed = new double[0];
        aee = new boolean[0];
        aef = new String[0];
        aeg = new byte[0][];
        aeh = new byte[0];
    }
    
    public static final int b(final kn kn, final int n) throws IOException {
        int n2 = 1;
        final int position = kn.getPosition();
        kn.cQ(n);
        while (kn.ms() > 0 && kn.mh() == n) {
            kn.cQ(n);
            ++n2;
        }
        kn.cT(position);
        return n2;
    }
    
    static int de(final int n) {
        return n & 0x7;
    }
    
    public static int df(final int n) {
        return n >>> 3;
    }
    
    static int l(final int n, final int n2) {
        return n << 3 | n2;
    }
}
