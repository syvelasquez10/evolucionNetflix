// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;
import android.app.NotificationManager;

interface NotificationManagerCompat$Impl
{
    void cancelNotification(final NotificationManager p0, final String p1, final int p2);
    
    int getSideChannelBindFlags();
    
    void postNotification(final NotificationManager p0, final String p1, final int p2, final Notification p3);
}
