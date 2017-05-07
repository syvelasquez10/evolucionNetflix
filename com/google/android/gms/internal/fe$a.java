// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

final class fe$a extends Exception
{
    private final int tc;
    
    public fe$a(final String s, final int tc) {
        super(s);
        this.tc = tc;
    }
    
    public int getErrorCode() {
        return this.tc;
    }
}
