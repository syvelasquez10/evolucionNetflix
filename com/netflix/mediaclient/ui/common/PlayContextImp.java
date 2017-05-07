// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayContextImp implements PlayContext
{
    public static final Parcelable$Creator<PlayContextImp> CREATOR;
    private final int listPos;
    private final String requestId;
    private final int trackId;
    private final int videoPos;
    
    static {
        CREATOR = (Parcelable$Creator)new PlayContextImp$1();
    }
    
    PlayContextImp() {
        this("", -1, -1, -1);
    }
    
    public PlayContextImp(final Parcel parcel) {
        this(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
    }
    
    public PlayContextImp(final Trackable trackable, final int n) {
        this(trackable.getRequestId(), trackable.getTrackId(), trackable.getListPos(), n);
    }
    
    public PlayContextImp(final String requestId, final int trackId, final int listPos, final int videoPos) {
        this.requestId = requestId;
        this.trackId = trackId;
        this.listPos = listPos;
        this.videoPos = videoPos;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public int getHeroTrackId() {
        throw new UnsupportedOperationException("Should not be needed");
    }
    
    public int getListPos() {
        return this.listPos;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public int getVideoPos() {
        return this.videoPos;
    }
    
    public boolean isHero() {
        return false;
    }
    
    @Override
    public String toString() {
        return "PlayContextImp [requestId=" + this.requestId + ", trackId=" + this.trackId + ", listPos=" + this.listPos + ", videoPos=" + this.videoPos + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.requestId);
        parcel.writeInt(this.trackId);
        parcel.writeInt(this.listPos);
        parcel.writeInt(this.videoPos);
    }
}
