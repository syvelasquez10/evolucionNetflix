// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import com.crittercism.app.Crittercism$LoggingLevel;

public enum dw$b
{
    a("Silent", 0, 0), 
    b("Error", 1, 100), 
    c("Warning", 2, 200), 
    d("Info", 3, 300), 
    e("Debug", 4, 400), 
    f("Verbose", 5, 500);
    
    private int g;
    
    private dw$b(final String s, final int n, final int g) {
        this.g = g;
    }
    
    public static dw$b a(final Crittercism$LoggingLevel crittercism$LoggingLevel) {
        switch (dw$1.b[crittercism$LoggingLevel.ordinal()]) {
            default: {
                return dw$b.c;
            }
            case 1: {
                return dw$b.a;
            }
            case 2: {
                return dw$b.b;
            }
            case 3: {
                return dw$b.c;
            }
            case 4: {
                return dw$b.d;
            }
        }
    }
    
    public final boolean a(final dw$b dw$b) {
        return this.g >= dw$b.g;
    }
}
