// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.util.Locale;

public final class cg
{
    public static final cg a;
    private volatile int b;
    private final long c;
    
    static {
        a = new cg();
    }
    
    private cg() {
        this.b = 1;
        this.c = System.currentTimeMillis();
    }
    
    private int b() {
        synchronized (this) {
            return this.b++;
        }
    }
    
    public final String a() {
        return String.format(Locale.US, "%d.%d.%09d", 1, this.c, this.b());
    }
}
