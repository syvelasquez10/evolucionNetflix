// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

public class FacebookDialogException extends FacebookException
{
    private int errorCode;
    private String failingUrl;
    
    public FacebookDialogException(final String s, final int errorCode, final String failingUrl) {
        super(s);
        this.errorCode = errorCode;
        this.failingUrl = failingUrl;
    }
}
