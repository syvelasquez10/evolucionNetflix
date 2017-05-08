// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public class MslInternalException extends RuntimeException
{
    private static final long serialVersionUID = 5787827728910061805L;
    
    public MslInternalException(final String s) {
        super(s);
    }
    
    public MslInternalException(final String s, final Throwable t) {
        super(s, t);
    }
}
