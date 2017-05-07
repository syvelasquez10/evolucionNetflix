// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.leafs.social;

import com.netflix.model.branches.FalkorVideo;
import com.netflix.falkor.ModelProxy;
import com.netflix.model.branches.FalkorIrisNotification;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;
import com.netflix.mediaclient.servicemgr.interface_.search.IrisNotificationsList;
import android.os.Parcelable;

public class IrisNotificationsListImpl implements Parcelable, IrisNotificationsList
{
    public static final Parcelable$Creator<IrisNotificationsListImpl> CREATOR;
    List<IrisNotificationSummary> notifications;
    IrisNotificationsListSummary summary;
    
    static {
        CREATOR = (Parcelable$Creator)new IrisNotificationsListImpl$1();
    }
    
    protected IrisNotificationsListImpl(final Parcel parcel) {
        this.summary = (IrisNotificationsListSummary)parcel.readParcelable(IrisNotificationsListSummary.class.getClassLoader());
        parcel.readList((List)(this.notifications = new ArrayList<IrisNotificationSummary>()), IrisNotificationSummary.class.getClassLoader());
    }
    
    public IrisNotificationsListImpl(final IrisNotificationsListSummary summary, final List<FalkorIrisNotification> list, final ModelProxy<?> modelProxy) {
        this.notifications = new ArrayList<IrisNotificationSummary>();
        for (int i = 0; i < list.size(); ++i) {
            final FalkorIrisNotification falkorIrisNotification = list.get(i);
            falkorIrisNotification.summary.fillVideoDetails((FalkorVideo)modelProxy.getValue(falkorIrisNotification.video.getRefPath()));
            this.notifications.add(falkorIrisNotification.summary);
        }
        this.summary = summary;
    }
    
    public IrisNotificationsListImpl(final List<IrisNotificationSummary> notifications, final IrisNotificationsListSummary summary) {
        this.notifications = notifications;
        this.summary = summary;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Parcelable getParcelable() {
        return (Parcelable)this;
    }
    
    public List<IrisNotificationSummary> getSocialNotifications() {
        return this.notifications;
    }
    
    public IrisNotificationsListSummary getSocialNotificationsListSummary() {
        return this.summary;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.summary, n);
        parcel.writeList((List)this.notifications);
    }
}
