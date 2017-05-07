// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplBase implements NotificationCompat$NotificationCompatImpl
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder) {
        final Notification mNotification = notificationCompat$Builder.mNotification;
        mNotification.setLatestEventInfo(notificationCompat$Builder.mContext, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentIntent);
        if (notificationCompat$Builder.mPriority > 0) {
            mNotification.flags |= 0x80;
        }
        return mNotification;
    }
}
