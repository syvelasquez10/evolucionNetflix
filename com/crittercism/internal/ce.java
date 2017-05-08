// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.util.Locale;

public final class ce
{
    public static final ce a;
    private volatile int b;
    private final long c;
    
    static {
        a = new ce();
    }
    
    private ce() {
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
