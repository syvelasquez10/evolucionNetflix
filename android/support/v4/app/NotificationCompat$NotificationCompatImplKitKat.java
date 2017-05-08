// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.Notification;

class NotificationCompat$NotificationCompatImplKitKat extends NotificationCompat$NotificationCompatImplJellybean
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BuilderExtender notificationCompat$BuilderExtender) {
        final NotificationCompatKitKat$Builder notificationCompatKitKat$Builder = new NotificationCompatKitKat$Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mNotification, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentInfo, notificationCompat$Builder.mTickerView, notificationCompat$Builder.mNumber, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent, notificationCompat$Builder.mLargeIcon, notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate, notificationCompat$Builder.mShowWhen, notificationCompat$Builder.mUseChronometer, notificationCompat$Builder.mPriority, notificationCompat$Builder.mSubText, notificationCompat$Builder.mLocalOnly, notificationCompat$Builder.mPeople, notificationCompat$Builder.mExtras, notificationCompat$Builder.mGroupKey, notificationCompat$Builder.mGroupSummary, notificationCompat$Builder.mSortKey, notificationCompat$Builder.mContentView, notificationCompat$Builder.mBigContentView);
        NotificationCompat.addActionsToBuilder(notificationCompatKitKat$Builder, notificationCompat$Builder.mActions);
        NotificationCompat.addStyleToBuilderJellybean(notificationCompatKitKat$Builder, notificationCompat$Builder.mStyle);
        return notificationCompat$BuilderExtender.build(notificationCompat$Builder, notificationCompatKitKat$Builder);
    }
    
    @Override
    public NotificationCompat$Action getAction(final Notification notification, final int n) {
        return (NotificationCompat$Action)NotificationCompatKitKat.getAction(notification, n, NotificationCompat$Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public int getActionCount(final Notification notification) {
        return NotificationCompatKitKat.getActionCount(notification);
    }
    
    @Override
    public Bundle getExtras(final Notification notification) {
        return NotificationCompatKitKat.getExtras(notification);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatKitKat.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatKitKat.getLocalOnly(notification);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatKitKat.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatKitKat.isGroupSummary(notification);
    }
}
