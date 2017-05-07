// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.Log;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import com.netflix.mediaclient.ui.voip.ContactUsActivity;
import android.content.Intent;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat$Builder;
import android.app.Notification;
import java.util.concurrent.atomic.AtomicBoolean;
import android.app.NotificationManager;
import android.content.Context;
import android.annotation.SuppressLint;

@SuppressLint({ "InlinedApi" })
class CallNotificationManager
{
    private static final String CALL_NOTIFICATION_ACTION_CANCEL = "com.netflix.mediaclient.intent.action.CALL_CANCEL";
    private static final int NOTIFICATION_ID = 20;
    private static final String TAG = "nf_voip";
    private Context mContext;
    private NotificationManager mNotificationManager;
    private AtomicBoolean mShowNotification;
    
    CallNotificationManager(final Context mContext) {
        this.mShowNotification = new AtomicBoolean(false);
        this.mContext = mContext;
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    }
    
    private Notification createNotification(final boolean b) {
        final String string = this.mContext.getString(2131165467);
        final String string2 = this.mContext.getString(2131165468);
        String contentText;
        if (b) {
            contentText = this.mContext.getString(2131165469);
        }
        else {
            contentText = this.mContext.getString(2131165470);
        }
        final Notification build = new NotificationCompat$Builder(this.mContext).setOngoing(true).setOnlyAlertOnce(true).setCategory("call").setSmallIcon(2130837785).setLargeIcon(this.getLargeIcon()).setPriority(2).setContentTitle(string).setContentText(contentText).setTicker(string).setContentIntent(this.createNotificationPendingIntentResume()).setDeleteIntent(this.createNotificationPendingIntentDelete()).addAction(2130837700, string2, this.createNotificationPendingIntentDelete()).setAutoCancel(false).setWhen(System.currentTimeMillis()).build();
        this.mNotificationManager.notify(20, build);
        return build;
    }
    
    private PendingIntent createNotificationPendingIntentDelete() {
        return PendingIntent.getBroadcast(this.mContext, 0, new Intent("com.netflix.mediaclient.intent.action.CALL_CANCEL").addCategory("com.netflix.mediaclient.intent.category.VOIP"), 268435456);
    }
    
    private PendingIntent createNotificationPendingIntentResume() {
        return PendingIntent.getActivity(this.mContext, 0, ContactUsActivity.createStartIntent(this.mContext), 268435456);
    }
    
    private Bitmap getLargeIcon() {
        return BitmapFactory.decodeResource(this.mContext.getResources(), 2130837742);
    }
    
    public static IntentFilter getNotificationIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.netflix.mediaclient.intent.action.CALL_CANCEL");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.VOIP");
        intentFilter.setPriority(999);
        return intentFilter;
    }
    
    public static boolean isDelete(final String s) {
        return "com.netflix.mediaclient.intent.action.CALL_CANCEL".equalsIgnoreCase(s);
    }
    
    void cancelNotification() {
        Log.d("nf_voip", "Cancel notification");
        this.mShowNotification.set(false);
        this.mNotificationManager.cancel(20);
    }
    
    void showCallinglNotification() {
        this.mShowNotification.set(true);
        this.mNotificationManager.notify(20, this.createNotification(false));
    }
    
    void updateConnectedNotification() {
        this.mShowNotification.set(true);
        this.mNotificationManager.notify(20, this.createNotification(true));
    }
}
