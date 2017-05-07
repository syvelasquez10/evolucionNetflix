// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.app.NotificationManager;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import android.app.Notification;
import android.media.AudioManager;
import com.netflix.mediaclient.util.NotificationUtils;
import java.util.Locale;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.Context;

abstract class NotificationBuilder
{
    protected static final String GUID = "guid";
    protected static final String MESSAGE_GUID = "messageGuid";
    protected static final String ORIGINATOR = "originator";
    protected static final String TAG = "nf_push";
    public static final String TARGET_URL = "target_url";
    
    protected static PendingIntent getNotificationCanceledIntent(final Context context, final Payload payload) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
        }
        if (StringUtils.isNotEmpty(payload.originator)) {
            intent.putExtra("originator", payload.originator);
        }
        return PendingIntent.getBroadcast(context, 0, intent, 268435456);
    }
    
    protected static PendingIntent getNotificationOpenedIntent(final Context context, final Uri data, final Payload payload) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(data);
        intent.setFlags(872415232);
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
        }
        if (StringUtils.isNotEmpty(payload.originator)) {
            intent.putExtra("originator", payload.originator);
        }
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
    
    protected static PendingIntent getNotificationOpenedIntent(final Context context, final Payload payload) {
        final Uri defaultActionPayload = payload.getDefaultActionPayload();
        if (defaultActionPayload != null) {
            final String scheme = defaultActionPayload.getScheme();
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "getNotificationOpenedIntent: schema for default action uri: " + scheme);
            }
            if (scheme != null && "https".equals(scheme.toLowerCase(Locale.US))) {
                Log.d("nf_push", "Target destination is web site (https)");
                return getNotificationOpenedIntentForBrowserRedirect(context, defaultActionPayload, payload);
            }
        }
        Log.d("nf_push", "Target destination is our application");
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(defaultActionPayload);
        NotificationUtils.addNotificationSourceToIntent(intent);
        intent.setFlags(872415232);
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
        }
        if (StringUtils.isNotEmpty(payload.originator)) {
            intent.putExtra("originator", payload.originator);
        }
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
    
    protected static PendingIntent getNotificationOpenedIntentForBrowserRedirect(final Context context, final Uri uri, final Payload payload) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT");
        intent.putExtra("target_url", uri.toString());
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
        }
        if (StringUtils.isNotEmpty(payload.originator)) {
            intent.putExtra("originator", payload.originator);
        }
        return PendingIntent.getBroadcast(context, 0, intent, 268435456);
    }
    
    protected static Uri getSound(final String s) {
        if (s != null && s.trim().toLowerCase(Locale.US).startsWith("http")) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "CDN sound: " + s);
            }
            return Uri.parse(s);
        }
        Log.d("nf_push", "default sound");
        return Uri.parse("android.resource://com.netflix.mediaclient/2131099649");
    }
    
    protected static boolean isSoundEnabled(final Context context) {
        switch (((AudioManager)context.getSystemService("audio")).getRingerMode()) {
            case 0: {
                return false;
            }
            case 1: {
                return false;
            }
            case 2: {
                Log.i("nf_push", "Normal mode");
                break;
            }
        }
        return true;
    }
    
    protected static void sendNotification(final Context context, final Notification notification, final int n, final ErrorLogging errorLogging) {
        final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
        if (notificationManager != null) {
            try {
                notificationManager.notify(n, notification);
                return;
            }
            catch (SecurityException ex) {
                Log.e("nf_push", "We are missing privilege?", ex);
                errorLogging.logHandledException(ex);
                return;
            }
            catch (Throwable t) {
                Log.e("nf_push", "Unexpected failure when trying to send notification", t);
                errorLogging.logHandledException(new RuntimeException("Unexpected failure when trying to send notification", t));
                return;
            }
        }
        Log.e("nf_push", "Notification manager is not available!");
    }
}
