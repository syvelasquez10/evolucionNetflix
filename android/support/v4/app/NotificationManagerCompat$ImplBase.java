// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;

class NotificationManagerCompat$ImplBase implements NotificationManagerCompat$Impl
{
    @Override
    public boolean areNotificationsEnabled(final Context context, final NotificationManager notificationManager) {
        return true;
    }
    
    @Override
    public void cancelNotification(final NotificationManager notificationManager, final String s, final int n) {
        notificationManager.cancel(s, n);
    }
    
    @Override
    public int getImportance(final NotificationManager notificationManager) {
        return -1000;
    }
    
    @Override
    public int getSideChannelBindFlags() {
        return 1;
    }
    
    @Override
    public void postNotification(final NotificationManager notificationManager, final String s, final int n, final Notification notification) {
        notificationManager.notify(s, n, notification);
    }
}
