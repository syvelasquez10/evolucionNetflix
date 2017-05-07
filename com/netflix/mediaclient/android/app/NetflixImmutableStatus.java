// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.StatusCode;

public final class NetflixImmutableStatus implements Status
{
    private final StatusCode mStatusCode;
    
    NetflixImmutableStatus(final StatusCode mStatusCode) {
        this.mStatusCode = mStatusCode;
    }
    
    @Override
    public Error getError() {
        return null;
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
        return "NetflixImmutableStatus, " + this.mStatusCode;
    }
}
