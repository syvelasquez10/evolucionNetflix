// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;

public class TrackableObject implements Trackable
{
    private final int listPos;
    private final String reqId;
    private final int trackId;
    
    public TrackableObject(final String reqId, final int trackId, final int listPos) {
        this.reqId = reqId;
        this.trackId = trackId;
        this.listPos = listPos;
    }
    
    @Override
    public int getHeroTrackId() {
        throw new UnsupportedOperationException("Should not be needed");
    }
    
    @Override
    public int getListPos() {
        return this.listPos;
    }
    
    @Override
    public String getRequestId() {
        return this.reqId;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public boolean isHero() {
        return false;
    }
}
