// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookAuthorizationException extends FacebookException
{
    static final long serialVersionUID = 1L;
    
    public FacebookAuthorizationException() {
    }
    
    public FacebookAuthorizationException(final String s) {
        super(s);
    }
    
    public FacebookAuthorizationException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookAuthorizationException(final Throwable t) {
        super(t);
    }
}
