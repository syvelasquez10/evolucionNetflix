// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import android.util.SparseArray;

public enum b
{
    a("MOBILE", 0, 0), 
    b("WIFI", 1, 1), 
    c("UNKNOWN", 2, 2), 
    d("NOT_CONNECTED", 3, 3);
    
    private static SparseArray f;
    int e;
    
    static {
        (b.f = new SparseArray()).put(0, (Object)b.a);
        b.f.put(1, (Object)b.b);
    }
    
    private b(final String s, final int n, final int e) {
        this.e = e;
    }
    
    public static b a(final int n) {
        b c;
        if ((c = (b)b.f.get(n)) == null) {
            c = b.c;
        }
        return c;
    }
}
