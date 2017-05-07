// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookException extends RuntimeException
{
    static final long serialVersionUID = 1L;
    
    public FacebookException() {
    }
    
    public FacebookException(final String s) {
        super(s);
    }
    
    public FacebookException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookException(final Throwable t) {
        super(t);
    }
}
