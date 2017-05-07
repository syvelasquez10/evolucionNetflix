// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import android.util.SparseArray;

public enum b
{
    a("MOBILE", 0, 0), 
    b("WIFI", 1, 1), 
    c("UNKNOWN", 2, 2), 
    d("NOT_CONNECTED", 3, 3);
    
    private static SparseArray e;
    private int f;
    
    static {
        (b.e = new SparseArray()).put(0, (Object)b.a);
        b.e.put(1, (Object)b.b);
    }
    
    private b(final String s, final int n, final int f) {
        this.f = f;
    }
    
    public static b a(final int n) {
        b c;
        if ((c = (b)b.e.get(n)) == null) {
            c = b.c;
        }
        return c;
    }
    
    public final int a() {
        return this.f;
    }
}
