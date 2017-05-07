// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import android.os.Bundle;
import android.os.Build$VERSION;
import android.os.Parcelable;
import java.util.ArrayList;
import android.app.Notification;

class NotificationCompat$NotificationCompatImplApi20 extends NotificationCompat$NotificationCompatImplKitKat
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder) {
        final NotificationCompatApi20$Builder notificationCompatApi20$Builder = new NotificationCompatApi20$Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mNotification, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentInfo, notificationCompat$Builder.mTickerView, notificationCompat$Builder.mNumber, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent, notificationCompat$Builder.mLargeIcon, notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate, notificationCompat$Builder.mShowWhen, notificationCompat$Builder.mUseChronometer, notificationCompat$Builder.mPriority, notificationCompat$Builder.mSubText, notificationCompat$Builder.mLocalOnly, notificationCompat$Builder.mPeople, notificationCompat$Builder.mExtras, notificationCompat$Builder.mGroupKey, notificationCompat$Builder.mGroupSummary, notificationCompat$Builder.mSortKey);
        addActionsToBuilder(notificationCompatApi20$Builder, notificationCompat$Builder.mActions);
        addStyleToBuilderJellybean(notificationCompatApi20$Builder, notificationCompat$Builder.mStyle);
        return notificationCompatApi20$Builder.build();
    }
    
    @Override
    public NotificationCompat$Action getAction(final Notification notification, final int n) {
        return (NotificationCompat$Action)NotificationCompatApi20.getAction(notification, n, NotificationCompat$Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public NotificationCompat$Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
        return (NotificationCompat$Action[])NotificationCompatApi20.getActionsFromParcelableArrayList(list, NotificationCompat$Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatApi20.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatApi20.getLocalOnly(notification);
    }
    
    @Override
    public ArrayList<Parcelable> getParcelableArrayListForActions(final NotificationCompat$Action[] array) {
        return NotificationCompatApi20.getParcelableArrayListForActions(array);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatApi20.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatApi20.isGroupSummary(notification);
    }
}
