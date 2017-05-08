// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public final class cj extends Exception
{
    private static final long serialVersionUID = 4511293437269420307L;
    
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
