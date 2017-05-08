// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class cj extends Exception
{
    public cj(final String s) {
        this(s, null);
    }
    
    public cj(final String s, final Throwable t) {
        super(s, t);
    }
    
    public cj(final Throwable t) {
        super(t);
    }
}
