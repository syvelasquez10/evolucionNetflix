// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcelable;
import java.util.ArrayList;
import android.app.Notification;

class NotificationCompat$NotificationCompatImplJellybean extends NotificationCompat$NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BuilderExtender notificationCompat$BuilderExtender) {
        final NotificationCompatJellybean$Builder notificationCompatJellybean$Builder = new NotificationCompatJellybean$Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mNotification, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentInfo, notificationCompat$Builder.mTickerView, notificationCompat$Builder.mNumber, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent, notificationCompat$Builder.mLargeIcon, notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate, notificationCompat$Builder.mUseChronometer, notificationCompat$Builder.mPriority, notificationCompat$Builder.mSubText, notificationCompat$Builder.mLocalOnly, notificationCompat$Builder.mExtras, notificationCompat$Builder.mGroupKey, notificationCompat$Builder.mGroupSummary, notificationCompat$Builder.mSortKey, notificationCompat$Builder.mContentView, notificationCompat$Builder.mBigContentView);
        NotificationCompat.addActionsToBuilder(notificationCompatJellybean$Builder, notificationCompat$Builder.mActions);
        NotificationCompat.addStyleToBuilderJellybean(notificationCompatJellybean$Builder, notificationCompat$Builder.mStyle);
        final Notification build = notificationCompat$BuilderExtender.build(notificationCompat$Builder, notificationCompatJellybean$Builder);
        if (notificationCompat$Builder.mStyle != null) {
            notificationCompat$Builder.mStyle.addCompatExtras(this.getExtras(build));
        }
        return build;
    }
    
    @Override
    public NotificationCompat$Action getAction(final Notification notification, final int n) {
        return (NotificationCompat$Action)NotificationCompatJellybean.getAction(notification, n, NotificationCompat$Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public int getActionCount(final Notification notification) {
        return NotificationCompatJellybean.getActionCount(notification);
    }
    
    @Override
    public NotificationCompat$Action[] getActionsFromParcelableArrayList(final ArrayList<Parcelable> list) {
        return (NotificationCompat$Action[])NotificationCompatJellybean.getActionsFromParcelableArrayList(list, NotificationCompat$Action.FACTORY, RemoteInput.FACTORY);
    }
    
    @Override
    public Bundle getExtras(final Notification notification) {
        return NotificationCompatJellybean.getExtras(notification);
    }
    
    @Override
    public String getGroup(final Notification notification) {
        return NotificationCompatJellybean.getGroup(notification);
    }
    
    @Override
    public boolean getLocalOnly(final Notification notification) {
        return NotificationCompatJellybean.getLocalOnly(notification);
    }
    
    @Override
    public ArrayList<Parcelable> getParcelableArrayListForActions(final NotificationCompat$Action[] array) {
        return NotificationCompatJellybean.getParcelableArrayListForActions(array);
    }
    
    @Override
    public String getSortKey(final Notification notification) {
        return NotificationCompatJellybean.getSortKey(notification);
    }
    
    @Override
    public boolean isGroupSummary(final Notification notification) {
        return NotificationCompatJellybean.isGroupSummary(notification);
    }
}
