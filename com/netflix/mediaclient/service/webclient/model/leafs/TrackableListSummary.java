// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;

@Deprecated
public class TrackableListSummary extends ListSummary implements Trackable
{
    private int heroTrackId;
    private int listPos;
    private String requestId;
    private int trackId;
    
    public TrackableListSummary() {
        super(0);
    }
    
    protected TrackableListSummary(final int n, final int trackId, final int listPos, final String requestId) {
        super(n);
        this.trackId = trackId;
        this.listPos = listPos;
        this.requestId = requestId;
    }
    
    protected TrackableListSummary(final Parcel parcel) {
        super(parcel);
        this.trackId = parcel.readInt();
        this.listPos = parcel.readInt();
        this.requestId = parcel.readString();
    }
    
    @Override
    public int getHeroTrackId() {
        return this.heroTrackId;
    }
    
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
    
    @Override
    public boolean isHero() {
        return false;
    }
    
    public void setListPos(final int listPos) {
        this.listPos = listPos;
    }
    
    @Override
    public String toString() {
        return "TrackableListSummary [trackId=" + this.trackId + ", listPos=" + this.listPos + ", requestId=" + this.requestId + "]";
    }
    
    @Override
    protected void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeInt(this.trackId);
        parcel.writeInt(this.listPos);
        parcel.writeString(this.requestId);
    }
}
