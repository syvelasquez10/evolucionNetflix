// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

public class MslRuntimeException extends RuntimeException
{
    public MslRuntimeException(final String s) {
        super(s);
    }
    
    public MslRuntimeException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public MslRuntimeException(final Throwable t) {
        super(t);
    }
}
