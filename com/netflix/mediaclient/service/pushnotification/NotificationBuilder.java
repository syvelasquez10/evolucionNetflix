// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import android.app.NotificationManager;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import android.app.Notification;
import com.netflix.mediaclient.Log;
import java.util.Locale;
import android.net.Uri;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import android.app.PendingIntent;
import android.content.Context;

abstract class NotificationBuilder
{
    protected static final String GUID = "guid";
    protected static final String MESSAGE_GUID = "messageGuid";
    protected static final String TAG = "nf_push";
    
    protected static PendingIntent getNotificationCanceledIntent(final Context context, final Payload payload) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
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
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
    
    protected static PendingIntent getNotificationOpenedIntent(final Context context, final Payload payload) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        final Uri defaultActionPayload = payload.getDefaultActionPayload();
        if (defaultActionPayload != null) {
            intent.setData(defaultActionPayload);
        }
        intent.setFlags(872415232);
        if (!StringUtils.isEmpty(payload.guid)) {
            intent.putExtra("guid", payload.guid);
        }
        if (!StringUtils.isEmpty(payload.messageGuid)) {
            intent.putExtra("messageGuid", payload.messageGuid);
        }
        return PendingIntent.getActivity(context, 0, intent, 134217728);
    }
    
    protected static Uri getSound(final String s) {
        if (s != null && s.trim().toLowerCase(Locale.US).startsWith("http")) {
            if (Log.isLoggable("nf_push", 3)) {
                Log.d("nf_push", "CDN sound: " + s);
            }
            return Uri.parse(s);
        }
        Log.d("nf_push", "default sound");
        return Uri.parse("android.resource://com.netflix.mediaclient/2131034113");
    }
    
    protected static boolean isSoundEnabled(final Context context) {
        return false;
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
