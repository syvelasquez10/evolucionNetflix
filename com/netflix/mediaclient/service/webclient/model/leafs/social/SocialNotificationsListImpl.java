// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs.social;

import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class SocialNotificationsListImpl implements Parcelable, SocialNotificationsList
{
    public static final Parcelable$Creator<SocialNotificationsListImpl> CREATOR;
    List<SocialNotificationSummary> notifications;
    SocialNotificationsListSummary summary;
    
    static {
        CREATOR = (Parcelable$Creator)new SocialNotificationsListImpl$1();
    }
    
    protected SocialNotificationsListImpl(final Parcel parcel) {
        this.summary = (SocialNotificationsListSummary)parcel.readParcelable(SocialNotificationsListSummary.class.getClassLoader());
        parcel.readList((List)(this.notifications = new ArrayList<SocialNotificationSummary>()), SocialNotificationSummary.class.getClassLoader());
    }
    
    public SocialNotificationsListImpl(final List<SocialNotificationSummary> notifications, final SocialNotificationsListSummary summary) {
        this.notifications = notifications;
        this.summary = summary;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Parcelable getParcelable() {
        return (Parcelable)this;
    }
    
    public List<SocialNotificationSummary> getSocialNotifications() {
        return this.notifications;
    }
    
    public SocialNotificationsListSummary getSocialNotificationsListSummary() {
        return this.summary;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.summary, n);
        parcel.writeList((List)this.notifications);
    }
}
