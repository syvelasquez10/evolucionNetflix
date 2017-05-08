// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.os.Bundle;
import android.app.Notification;
import android.content.ComponentName;
import android.provider.Settings$Secure;
import android.os.Build$VERSION;
import android.support.v4.os.BuildCompat;
import java.util.HashSet;
import android.app.NotificationManager;
import android.content.Context;
import java.util.Set;

public final class NotificationManagerCompat
{
    public static final String ACTION_BIND_SIDE_CHANNEL = "android.support.BIND_NOTIFICATION_SIDE_CHANNEL";
    public static final String EXTRA_USE_SIDE_CHANNEL = "android.support.useSideChannel";
    private static final NotificationManagerCompat$Impl IMPL;
    public static final int IMPORTANCE_DEFAULT = 3;
    public static final int IMPORTANCE_HIGH = 4;
    public static final int IMPORTANCE_LOW = 2;
    public static final int IMPORTANCE_MAX = 5;
    public static final int IMPORTANCE_MIN = 1;
    public static final int IMPORTANCE_NONE = 0;
    public static final int IMPORTANCE_UNSPECIFIED = -1000;
    static final int MAX_SIDE_CHANNEL_SDK_VERSION = 19;
    private static final String SETTING_ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    static final int SIDE_CHANNEL_BIND_FLAGS;
    private static final int SIDE_CHANNEL_RETRY_BASE_INTERVAL_MS = 1000;
    private static final int SIDE_CHANNEL_RETRY_MAX_COUNT = 6;
    private static final String TAG = "NotifManCompat";
    private static Set<String> sEnabledNotificationListenerPackages;
    private static String sEnabledNotificationListeners;
    private static final Object sEnabledNotificationListenersLock;
    private static final Object sLock;
    private static NotificationManagerCompat$SideChannelManager sSideChannelManager;
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    
    static {
        sEnabledNotificationListenersLock = new Object();
        NotificationManagerCompat.sEnabledNotificationListenerPackages = new HashSet<String>();
        sLock = new Object();
        if (BuildCompat.isAtLeastN()) {
            IMPL = new NotificationManagerCompat$ImplApi24();
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            IMPL = new NotificationManagerCompat$ImplKitKat();
        }
        else if (Build$VERSION.SDK_INT >= 14) {
            IMPL = new NotificationManagerCompat$ImplIceCreamSandwich();
        }
        else {
            IMPL = new NotificationManagerCompat$ImplBase();
        }
        SIDE_CHANNEL_BIND_FLAGS = NotificationManagerCompat.IMPL.getSideChannelBindFlags();
    }
    
    private NotificationManagerCompat(final Context mContext) {
        this.mContext = mContext;
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    }
    
    public static NotificationManagerCompat from(final Context context) {
        return new NotificationManagerCompat(context);
    }
    
    public static Set<String> getEnabledListenerPackages(Context sEnabledNotificationListenersLock) {
        final String string = Settings$Secure.getString(sEnabledNotificationListenersLock.getContentResolver(), "enabled_notification_listeners");
        sEnabledNotificationListenersLock = (Context)NotificationManagerCompat.sEnabledNotificationListenersLock;
        // monitorenter(sEnabledNotificationListenersLock)
        Label_0100: {
            if (string == null) {
                break Label_0100;
            }
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0113: {
                        try {
                            if (!string.equals(NotificationManagerCompat.sEnabledNotificationListeners)) {
                                final String[] split = string.split(":");
                                final HashSet sEnabledNotificationListenerPackages = new HashSet<String>(split.length);
                                final int length = split.length;
                                n = 0;
                                if (n < length) {
                                    final ComponentName unflattenFromString = ComponentName.unflattenFromString(split[n]);
                                    if (unflattenFromString != null) {
                                        sEnabledNotificationListenerPackages.add(unflattenFromString.getPackageName());
                                    }
                                    break Label_0113;
                                }
                                else {
                                    NotificationManagerCompat.sEnabledNotificationListenerPackages = (Set<String>)sEnabledNotificationListenerPackages;
                                    NotificationManagerCompat.sEnabledNotificationListeners = string;
                                }
                            }
                            return NotificationManagerCompat.sEnabledNotificationListenerPackages;
                        }
                        finally {
                        }
                        // monitorexit(sEnabledNotificationListenersLock)
                    }
                    ++n;
                    continue;
                }
            }
        }
    }
    
    private void pushSideChannelQueue(final NotificationManagerCompat$Task notificationManagerCompat$Task) {
        synchronized (NotificationManagerCompat.sLock) {
            if (NotificationManagerCompat.sSideChannelManager == null) {
                NotificationManagerCompat.sSideChannelManager = new NotificationManagerCompat$SideChannelManager(this.mContext.getApplicationContext());
            }
            NotificationManagerCompat.sSideChannelManager.queueTask(notificationManagerCompat$Task);
        }
    }
    
    private static boolean useSideChannelForNotification(final Notification notification) {
        final Bundle extras = NotificationCompat.getExtras(notification);
        return extras != null && extras.getBoolean("android.support.useSideChannel");
    }
    
    public boolean areNotificationsEnabled() {
        return NotificationManagerCompat.IMPL.areNotificationsEnabled(this.mContext, this.mNotificationManager);
    }
    
    public void cancel(final int n) {
        this.cancel(null, n);
    }
    
    public void cancel(final String s, final int n) {
        NotificationManagerCompat.IMPL.cancelNotification(this.mNotificationManager, s, n);
        if (Build$VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new NotificationManagerCompat$CancelTask(this.mContext.getPackageName(), n, s));
        }
    }
    
    public void cancelAll() {
        this.mNotificationManager.cancelAll();
        if (Build$VERSION.SDK_INT <= 19) {
            this.pushSideChannelQueue(new NotificationManagerCompat$CancelTask(this.mContext.getPackageName()));
        }
    }
    
    public int getImportance() {
        return NotificationManagerCompat.IMPL.getImportance(this.mNotificationManager);
    }
    
    public void notify(final int n, final Notification notification) {
        this.notify(null, n, notification);
    }
    
    public void notify(final String s, final int n, final Notification notification) {
        if (useSideChannelForNotification(notification)) {
            this.pushSideChannelQueue(new NotificationManagerCompat$NotifyTask(this.mContext.getPackageName(), n, s, notification));
            NotificationManagerCompat.IMPL.cancelNotification(this.mNotificationManager, s, n);
            return;
        }
        NotificationManagerCompat.IMPL.postNotification(this.mNotificationManager, s, n, notification);
    }
}
