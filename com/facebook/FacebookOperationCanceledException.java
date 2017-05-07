// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookOperationCanceledException extends FacebookException
{
    static final long serialVersionUID = 1L;
    
    public FacebookOperationCanceledException() {
    }
    
    public FacebookOperationCanceledException(final String s) {
        super(s);
    }
    
    public FacebookOperationCanceledException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FacebookOperationCanceledException(final Throwable t) {
        super(t);
    }
}
