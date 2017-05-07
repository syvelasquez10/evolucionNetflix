// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public final class pp
{
    public static final int[] awL;
    public static final long[] awM;
    public static final float[] awN;
    public static final double[] awO;
    public static final boolean[] awP;
    public static final String[] awQ;
    public static final byte[][] awR;
    public static final byte[] awS;
    
    static {
        awL = new int[0];
        awM = new long[0];
        awN = new float[0];
        awO = new double[0];
        awP = new boolean[0];
        awQ = new String[0];
        awR = new byte[0][];
        awS = new byte[0];
    }
    
    public static final int b(final pe pe, final int n) throws IOException {
        int n2 = 1;
        final int position = pe.getPosition();
        pe.gm(n);
        while (pe.qg() == n) {
            pe.gm(n);
            ++n2;
        }
        pe.gq(position);
        return n2;
    }
    
    static int gG(final int n) {
        return n & 0x7;
    }
    
    public static int gH(final int n) {
        return n >>> 3;
    }
    
    static int x(final int n, final int n2) {
        return n << 3 | n2;
    }
}
