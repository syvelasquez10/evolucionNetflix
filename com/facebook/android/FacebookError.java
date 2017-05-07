// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

public class FacebookError extends RuntimeException
{
    private int mErrorCode;
    
    public FacebookError(final String s) {
        super(s);
        this.mErrorCode = 0;
    }
}
