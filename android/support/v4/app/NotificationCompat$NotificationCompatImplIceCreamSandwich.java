// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplIceCreamSandwich extends NotificationCompat$NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder) {
        return NotificationCompatIceCreamSandwich.add(notificationCompat$Builder.mContext, notificationCompat$Builder.mNotification, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentInfo, notificationCompat$Builder.mTickerView, notificationCompat$Builder.mNumber, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent, notificationCompat$Builder.mLargeIcon, notificationCompat$Builder.mProgressMax, notificationCompat$Builder.mProgress, notificationCompat$Builder.mProgressIndeterminate);
    }
}
