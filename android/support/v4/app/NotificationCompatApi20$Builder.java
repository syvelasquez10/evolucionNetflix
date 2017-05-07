// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.app.PendingIntent;
import android.widget.RemoteViews;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.app.Notification$Builder;

public class NotificationCompatApi20$Builder implements NotificationBuilderWithActions, NotificationBuilderWithBuilderAccessor
{
    private Notification$Builder b;
    private Bundle mExtras;
    
    public NotificationCompatApi20$Builder(final Context context, final Notification notification, final CharSequence contentTitle, final CharSequence contentText, final CharSequence contentInfo, final RemoteViews remoteViews, final int number, final PendingIntent contentIntent, final PendingIntent pendingIntent, final Bitmap largeIcon, final int n, final int n2, final boolean b, final boolean showWhen, final boolean usesChronometer, final int priority, final CharSequence subText, final boolean localOnly, final ArrayList<String> list, final Bundle bundle, final String group, final boolean groupSummary, final String sortKey) {
        this.b = new Notification$Builder(context).setWhen(notification.when).setShowWhen(showWhen).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS).setOngoing((notification.flags & 0x2) != 0x0).setOnlyAlertOnce((notification.flags & 0x8) != 0x0).setAutoCancel((notification.flags & 0x10) != 0x0).setDefaults(notification.defaults).setContentTitle(contentTitle).setContentText(contentText).setSubText(subText).setContentInfo(contentInfo).setContentIntent(contentIntent).setDeleteIntent(notification.deleteIntent).setFullScreenIntent(pendingIntent, (notification.flags & 0x80) != 0x0).setLargeIcon(largeIcon).setNumber(number).setUsesChronometer(usesChronometer).setPriority(priority).setProgress(n, n2, b).setLocalOnly(localOnly).setGroup(group).setGroupSummary(groupSummary).setSortKey(sortKey);
        this.mExtras = new Bundle();
        if (bundle != null) {
            this.mExtras.putAll(bundle);
        }
        if (list != null && !list.isEmpty()) {
            this.mExtras.putStringArray("android.people", (String[])list.toArray(new String[list.size()]));
        }
    }
    
    @Override
    public void addAction(final NotificationCompatBase$Action notificationCompatBase$Action) {
        NotificationCompatApi20.addAction(this.b, notificationCompatBase$Action);
    }
    
    public Notification build() {
        this.b.setExtras(this.mExtras);
        return this.b.build();
    }
    
    @Override
    public Notification$Builder getBuilder() {
        return this.b;
    }
}
