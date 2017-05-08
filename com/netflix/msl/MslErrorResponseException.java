// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl;

public class MslErrorResponseException extends Exception
{
    private static final long serialVersionUID = 3844789699705189994L;
    private final Throwable requestCause;
    
    public MslErrorResponseException(final String s, final Throwable t, final Throwable requestCause) {
        super(s, t);
        this.requestCause = requestCause;
    }
    
    public Throwable getRequestCause() {
        return this.requestCause;
    }
}
