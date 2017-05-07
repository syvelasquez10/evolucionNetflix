// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.servicemgr.Trackable;

public class TrackableListSummary extends ListSummary implements Trackable
{
    private int listPos;
    private String requestId;
    private int trackId;
    
    @Override
    public int getListPos() {
        return this.listPos;
    }
    
    @Override
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int getTrackId() {
        return this.trackId;
    }
    
    public void setListPos(final int listPos) {
        this.listPos = listPos;
    }
    
    @Override
    public String toString() {
        return "TrackableListSummary [trackId=" + this.trackId + ", listPos=" + this.listPos + ", requestId=" + this.requestId + "]";
    }
}
