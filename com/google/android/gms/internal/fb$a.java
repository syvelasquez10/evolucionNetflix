// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

@ez
final class fb$a extends Exception
{
    private final int tc;
    
    public fb$a(final String s, final int tc) {
        super(s);
        this.tc = tc;
    }
    
    public int getErrorCode() {
        return this.tc;
    }
}
