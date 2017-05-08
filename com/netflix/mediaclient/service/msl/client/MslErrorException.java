// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.msl.msg.ErrorHeader;

public class MslErrorException extends Exception
{
    private ErrorHeader mErrorHeader;
    
    public MslErrorException(final ErrorHeader mErrorHeader) {
        this.mErrorHeader = mErrorHeader;
    }
    
    public MslErrorException(final String s) {
        super(s);
    }
    
    public MslErrorException(final String s, final ErrorHeader mErrorHeader) {
        super(s);
        this.mErrorHeader = mErrorHeader;
    }
    
    public ErrorHeader getErrorHeader() {
        return this.mErrorHeader;
    }
}
