// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

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
    
    public final boolean a(final dw$b dw$b) {
        return this.g >= dw$b.g;
    }
}
