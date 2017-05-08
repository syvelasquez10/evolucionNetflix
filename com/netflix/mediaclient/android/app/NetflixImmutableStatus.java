// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.StatusCode;

public final class NetflixImmutableStatus extends BaseStatus
{
    private static final long serialVersionUID = -5942279556763023564L;
    
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
    public boolean shouldDisplayMessage() {
        return false;
    }
    
    @Override
    public String toString() {
        return "NetflixImmutableStatus, " + this.mStatusCode;
    }
}
