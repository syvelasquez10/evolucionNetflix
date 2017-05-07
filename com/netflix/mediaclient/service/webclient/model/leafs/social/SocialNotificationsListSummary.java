// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import android.os.Parcel;
import com.google.gson.annotations.SerializedName;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class SocialNotificationsListSummary implements Parcelable
{
    public static final Parcelable$Creator<SocialNotificationsListSummary> CREATOR;
    @SerializedName("baseTrackId")
    private int baseTrackId;
    @SerializedName("length")
    private long length;
    @SerializedName("mdpTrackId")
    private int mdpTrackId;
    @SerializedName("playerTrackId")
    private int playerTrackId;
    @SerializedName("requestId")
    private String requestId;
    
    static {
        CREATOR = (Parcelable$Creator)new SocialNotificationsListSummary$1();
    }
    
    protected SocialNotificationsListSummary(final Parcel parcel) {
        final String[] array = new String[5];
        parcel.readStringArray(array);
        this.length = Long.valueOf(array[0]);
        this.requestId = array[1];
        this.baseTrackId = Integer.valueOf(array[2]);
        this.mdpTrackId = Integer.valueOf(array[3]);
        this.playerTrackId = Integer.valueOf(array[4]);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != null && o instanceof SocialNotificationsListSummary) {
            final SocialNotificationsListSummary socialNotificationsListSummary = (SocialNotificationsListSummary)o;
            if (this.getLength() == socialNotificationsListSummary.getLength() && this.getBaseTrackId() == socialNotificationsListSummary.getBaseTrackId() && this.getMDPTrackId() == socialNotificationsListSummary.getMDPTrackId() && this.getPlayerTrackId() == socialNotificationsListSummary.getPlayerTrackId()) {
                return true;
            }
        }
        return false;
    }
    
    public int getBaseTrackId() {
        return this.baseTrackId;
    }
    
    public long getLength() {
        return this.length;
    }
    
    public int getMDPTrackId() {
        return this.mdpTrackId;
    }
    
    public int getPlayerTrackId() {
        return this.playerTrackId;
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    @Override
    public int hashCode() {
        return (int)this.getLength();
    }
    
    @Override
    public String toString() {
        return "SocialNotificationsListSummary [length=" + this.length + ", requestId=" + this.requestId + ", baseTrackId=" + this.baseTrackId + ", mdpTrackId=" + this.mdpTrackId + ", playerTrackId=" + this.playerTrackId + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStringArray(new String[] { String.valueOf(this.length), this.requestId, String.valueOf(this.baseTrackId), String.valueOf(this.mdpTrackId), String.valueOf(this.playerTrackId) });
    }
}
