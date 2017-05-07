// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class kt
{
    protected volatile int adY;
    
    public kt() {
        this.adY = -1;
    }
    
    public static final <T extends kt> T a(final T t, final byte[] array) throws ks {
        return b(t, array, 0, array.length);
    }
    
    public static final void a(final kt kt, final byte[] array, final int n, final int n2) {
        try {
            final ko b = ko.b(array, n, n2);
            kt.a(b);
            b.mw();
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final <T extends kt> T b(final T t, final byte[] array, final int n, final int n2) throws ks {
        try {
            final kn a = kn.a(array, n, n2);
            t.b(a);
            a.cP(0);
            return t;
        }
        catch (ks ks) {
            throw ks;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }
    
    public static final byte[] d(final kt kt) {
        final byte[] array = new byte[kt.c()];
        a(kt, array, 0, array.length);
        return array;
    }
    
    public void a(final ko ko) throws IOException {
    }
    
    public abstract kt b(final kn p0) throws IOException;
    
    public int c() {
        return this.adY = this.mx();
    }
    
    public int mF() {
        if (this.adY < 0) {
            this.c();
        }
        return this.adY;
    }
    
    protected int mx() {
        return 0;
    }
    
    @Override
    public String toString() {
        return ku.e(this);
    }
}
