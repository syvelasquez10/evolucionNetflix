// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.StatusCode;

public final class NetflixImmutableStatus implements Status
{
    private final NetflixError[] mErrors;
    private final StatusCode mStatusCode;
    
    NetflixImmutableStatus(final StatusCode mStatusCode) {
        this.mErrors = new NetflixError[0];
        this.mStatusCode = mStatusCode;
    }
    
    @Override
    public NetflixError[] getErrors() {
        return this.mErrors;
    }
    
    @Override
    public String getMessage() {
        return null;
    }
    
    @Override
    public int getRequestId() {
        return Integer.MAX_VALUE;
    }
    
    @Override
    public StatusCode getStatusCode() {
        return this.mStatusCode;
    }
    
    @Override
    public boolean isError() {
        return !this.isSucces();
    }
    
    @Override
    public boolean isSucces() {
        return this.mStatusCode.getValue() >= StatusCode.OK.getValue();
    }
    
    @Override
    public boolean shouldDisplayMessage() {
        return false;
    }
    
    @Override
    public String toString() {
        return "NetflixImmutableStatus [statusCode=" + this.mStatusCode + "]";
    }
}
