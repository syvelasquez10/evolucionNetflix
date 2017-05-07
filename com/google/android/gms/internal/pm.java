// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class pm
{
    protected volatile int awJ;
    
    public pm() {
        this.awJ = -1;
    }
    
    public static final <T extends pm> T a(final T t, final byte[] array) {
        return b(t, array, 0, array.length);
    }
    
    public static final void a(final pm pm, final byte[] array, final int n, final int n2) {
        try {
            final pf b = pf.b(array, n, n2);
            pm.a(b);
            b.qw();
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final <T extends pm> T b(final T t, final byte[] array, final int n, final int n2) {
        try {
            final pe a = pe.a(array, n, n2);
            t.b(a);
            a.gl(0);
            return t;
        }
        catch (pl pl) {
            throw pl;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }
    
    public static final byte[] f(final pm pm) {
        final byte[] array = new byte[pm.qG()];
        a(pm, array, 0, array.length);
        return array;
    }
    
    public void a(final pf pf) {
    }
    
    public abstract pm b(final pe p0);
    
    protected int c() {
        return 0;
    }
    
    public int qF() {
        if (this.awJ < 0) {
            this.qG();
        }
        return this.awJ;
    }
    
    public int qG() {
        return this.awJ = this.c();
    }
    
    @Override
    public String toString() {
        return pn.g(this);
    }
}
