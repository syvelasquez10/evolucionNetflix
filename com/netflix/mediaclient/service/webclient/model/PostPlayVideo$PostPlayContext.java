// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.model.details.PostPlayContext;

public class PostPlayVideo$PostPlayContext implements PostPlayContext
{
    private final String requestId;
    private final int trackId;
    
    public PostPlayVideo$PostPlayContext(final int trackId, final String requestId) {
        this.trackId = trackId;
        this.requestId = requestId;
    }
    
    @Override
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
}
