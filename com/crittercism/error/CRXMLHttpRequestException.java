// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.error;

public class CRXMLHttpRequestException extends Exception
{
    private static final long serialVersionUID = 1515011187293165939L;
    
    public CRXMLHttpRequestException(final String s) {
        this(s, null);
    }
    
    public CRXMLHttpRequestException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public CRXMLHttpRequestException(final Throwable t) {
        super(t);
    }
}
