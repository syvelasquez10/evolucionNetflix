// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;

class NotificationCompat$NotificationCompatImplGingerbread extends NotificationCompat$NotificationCompatImplBase
{
    @Override
    public Notification build(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BuilderExtender notificationCompat$BuilderExtender) {
        final Notification mNotification = notificationCompat$Builder.mNotification;
        mNotification.setLatestEventInfo(notificationCompat$Builder.mContext, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentIntent);
        final Notification add = NotificationCompatGingerbread.add(mNotification, notificationCompat$Builder.mContext, notificationCompat$Builder.mContentTitle, notificationCompat$Builder.mContentText, notificationCompat$Builder.mContentIntent, notificationCompat$Builder.mFullScreenIntent);
        if (notificationCompat$Builder.mPriority > 0) {
            add.flags |= 0x80;
        }
        return add;
    }
}
