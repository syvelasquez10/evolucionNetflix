// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.error;

public class NrdNotInitializedException extends Exception
{
    private static final long serialVersionUID = 214614140429422155L;
    
    public NrdNotInitializedException() {
    }
    
    public NrdNotInitializedException(final String s) {
        super(s);
    }
    
    public NrdNotInitializedException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public NrdNotInitializedException(final Throwable t) {
        super(t);
    }
}
