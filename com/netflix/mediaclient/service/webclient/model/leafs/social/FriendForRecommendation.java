// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import android.os.Parcel;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class FriendForRecommendation implements Parcelable
{
    public static final Parcelable$Creator<FriendForRecommendation> CREATOR;
    private boolean bNetflixConnected;
    private boolean bWasRecommended;
    private boolean bWasWatched;
    private FriendProfile profile;
    
    static {
        CREATOR = (Parcelable$Creator)new FriendForRecommendation$1();
    }
    
    protected FriendForRecommendation(final Parcel parcel) {
        final String[] array = new String[7];
        parcel.readStringArray(array);
        this.bWasWatched = Boolean.valueOf(array[0]);
        this.bNetflixConnected = Boolean.valueOf(array[1]);
        this.bWasRecommended = Boolean.valueOf(array[2]);
        this.profile = new FriendProfile(array[3], array[4], array[5], array[6]);
    }
    
    public FriendForRecommendation(final FriendProfile profile, final boolean bWasWatched, final boolean bNetflixConnected) {
        this.profile = profile;
        this.bWasWatched = bWasWatched;
        this.bNetflixConnected = bNetflixConnected;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof FriendForRecommendation && this.profile.getId().equals(((FriendForRecommendation)o).profile.getId());
    }
    
    public FriendProfile getFriendProfile() {
        return this.profile;
    }
    
    @Override
    public int hashCode() {
        return this.profile.getId().hashCode();
    }
    
    public boolean isNetlflixConnected() {
        return this.bNetflixConnected;
    }
    
    public void setNetflixConnected(final boolean bNetflixConnected) {
        this.bNetflixConnected = bNetflixConnected;
    }
    
    public void setWasRecommended(final boolean bWasRecommended) {
        this.bWasRecommended = bWasRecommended;
    }
    
    public void setWasWatched(final boolean bWasWatched) {
        this.bWasWatched = bWasWatched;
    }
    
    @Override
    public String toString() {
        return new StringBuffer("Name: ").append(this.profile.getFullName()).append("; wasWatched: ").append(this.bWasWatched).append("; bNetflixConnected: ").append(this.bNetflixConnected).toString();
    }
    
    public boolean wasRecommended() {
        return this.bWasRecommended;
    }
    
    public boolean wasWatched() {
        return this.bWasWatched;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeStringArray(new String[] { String.valueOf(this.bWasWatched), String.valueOf(this.bNetflixConnected), String.valueOf(this.bWasRecommended), this.profile.getId(), this.profile.getFirstName(), this.profile.getLastName(), this.profile.getImageUrl() });
    }
}
