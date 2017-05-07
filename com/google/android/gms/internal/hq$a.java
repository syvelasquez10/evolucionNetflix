// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public final class hq$a
{
    private final List<hk> CA;
    private BitSet CB;
    private String CC;
    private String Cv;
    private boolean Cw;
    private int Cx;
    private boolean Cy;
    private String Cz;
    private final String mName;
    
    public hq$a(final String mName) {
        this.mName = mName;
        this.Cx = 1;
        this.CA = new ArrayList<hk>();
    }
    
    public hq$a E(final boolean cw) {
        this.Cw = cw;
        return this;
    }
    
    public hq$a F(final boolean cy) {
        this.Cy = cy;
        return this;
    }
    
    public hq$a P(final int n) {
        if (this.CB == null) {
            this.CB = new BitSet();
        }
        this.CB.set(n);
        return this;
    }
    
    public hq$a at(final String cv) {
        this.Cv = cv;
        return this;
    }
    
    public hq$a au(final String cc) {
        this.CC = cc;
        return this;
    }
    
    public hq fn() {
        int n = 0;
        int[] array = null;
        if (this.CB != null) {
            final int[] array2 = new int[this.CB.cardinality()];
            int n2 = this.CB.nextSetBit(0);
            while (true) {
                array = array2;
                if (n2 < 0) {
                    break;
                }
                array2[n] = n2;
                n2 = this.CB.nextSetBit(n2 + 1);
                ++n;
            }
        }
        return new hq(this.mName, this.Cv, this.Cw, this.Cx, this.Cy, this.Cz, this.CA.toArray(new hk[this.CA.size()]), array, this.CC);
    }
}
