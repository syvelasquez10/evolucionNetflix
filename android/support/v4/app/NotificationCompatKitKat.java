// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.util.SparseArray;
import android.app.Notification$Action;
import android.os.Bundle;
import android.app.Notification;

class NotificationCompatKitKat
{
    public static NotificationCompatBase$Action getAction(final Notification notification, final int n, final NotificationCompatBase$Action$Factory notificationCompatBase$Action$Factory, final RemoteInputCompatBase$RemoteInput$Factory remoteInputCompatBase$RemoteInput$Factory) {
        final Notification$Action notification$Action = notification.actions[n];
        final Bundle bundle = null;
        final SparseArray sparseParcelableArray = notification.extras.getSparseParcelableArray("android.support.actionExtras");
        Bundle bundle2 = bundle;
        if (sparseParcelableArray != null) {
            bundle2 = (Bundle)sparseParcelableArray.get(n);
        }
        return NotificationCompatJellybean.readAction(notificationCompatBase$Action$Factory, remoteInputCompatBase$RemoteInput$Factory, notification$Action.icon, notification$Action.title, notification$Action.actionIntent, bundle2);
    }
    
    public static int getActionCount(final Notification notification) {
        if (notification.actions != null) {
            return notification.actions.length;
        }
        return 0;
    }
    
    public static Bundle getExtras(final Notification notification) {
        return notification.extras;
    }
    
    public static String getGroup(final Notification notification) {
        return notification.extras.getString("android.support.groupKey");
    }
    
    public static boolean getLocalOnly(final Notification notification) {
        return notification.extras.getBoolean("android.support.localOnly");
    }
    
    public static String getSortKey(final Notification notification) {
        return notification.extras.getString("android.support.sortKey");
    }
    
    public static boolean isGroupSummary(final Notification notification) {
        return notification.extras.getBoolean("android.support.isGroupSummary");
    }
}
