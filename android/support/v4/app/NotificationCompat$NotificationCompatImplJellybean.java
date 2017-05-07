// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Iterator;
import android.os.Build$VERSION;
import java.util.ArrayList;
import android.app.Notification;

class NotificationCompat$NotificationCompatImplJellybean extends NotificationCompat$NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder) {
        final NotificationCompatJellybean$Builder notificationCompatJellybean$Builder = new NotificationCompatJellybean$Builder(notificationCompat$Builder.mContext, notificationCompat$Builder.mNotification, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentInfo, notificationCompat$Builder.mTickerView, notificationCompat$Builder.mNumber, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent, notificationCompat$Builder.mLargeIcon, notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate, notificationCompat$Builder.mUseChronometer, notificationCompat$Builder.mPriority, notificationCompat$Builder.mSubText, notificationCompat$Builder.mLocalOnly, notificationCompat$Builder.mExtras, notificationCompat$Builder.mGroupKey, notificationCompat$Builder.mGroupSummary, notificationCompat$Builder.mSortKey);
        addActionsToBuilder(notificationCompatJellybean$Builder, notificationCompat$Builder.mActions);
        addStyleToBuilderJellybean(notificationCompatJellybean$Builder, notificationCompat$Builder.mStyle);
        return notificationCompatJellybean$Builder.build();
    }
}
