// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookGraphObjectException extends FacebookException
{
    static final long serialVersionUID = 1L;
    
    public FacebookGraphObjectException() {
    }
    
    public FacebookGraphObjectException(final String s) {
        super(s);
    }
    
    public FacebookGraphObjectException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookGraphObjectException(final Throwable t) {
        super(t);
    }
}
