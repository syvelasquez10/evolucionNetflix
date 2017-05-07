// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.text.ParseException;
import android.util.SparseArray;

public enum e
{
    a("NOT_ON_WIFI", 0, 0), 
    b("ON_WIFI", 1, 1), 
    c("UNKNOWN", 2, 2);
    
    private static SparseArray d;
    private int e;
    
    static {
        (e.d = new SparseArray()).put(0, (Object)e.a);
        e.d.put(1, (Object)e.b);
    }
    
    private e(final String s, final int n, final int e) {
        this.e = e;
    }
    
    public static e a(final int n) {
        final e[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final e e = values[i];
            if (e.e == n) {
                return e;
            }
        }
        throw new ParseException("Unknown status code: " + Integer.toString(n), 0);
    }
    
    public final int a() {
        return this.e;
    }
}
