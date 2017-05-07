// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import android.content.Intent;

public final class NotificationUtils
{
    protected static final String PUSH_NOTIFACTION_SOURCE = "nflx_from_push_notification";
    protected static final String PUSH_NOTIFACTION_VALUE = "true";
    private static final String TAG = "nf_notification";
    
    public static void addNotificationSourceToIntent(final Intent intent) {
        if (intent != null) {
            intent.putExtra("nflx_from_push_notification", "true");
        }
    }
    
    public static boolean isIntentFromPushNotification(final Intent intent) {
        if (intent == null) {
            return false;
        }
        if ("true".equals(intent.getStringExtra("nflx_from_push_notification"))) {
            Log.v("nf_notification", "From push notification, report.");
            return true;
        }
        Log.d("nf_notification", "Not from push notification, do not report.");
        return false;
    }
}
