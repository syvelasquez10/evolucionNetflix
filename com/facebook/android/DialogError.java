// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.android;

public class DialogError extends Throwable
{
    private int mErrorCode;
    private String mFailingUrl;
    
    public DialogError(final String s, final int mErrorCode, final String mFailingUrl) {
        super(s);
        this.mErrorCode = mErrorCode;
        this.mFailingUrl = mFailingUrl;
    }
}
