// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.service.NetflixService;
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
    private final Context mContext;
    private final NotificationManager mNotificationManager;
    private final AtomicBoolean mShowNotification;
    
    CallNotificationManager(final Context mContext) {
        this.mShowNotification = new AtomicBoolean(false);
        this.mContext = mContext;
        this.mNotificationManager = (NotificationManager)this.mContext.getSystemService("notification");
    }
    
    private Notification createNotification(final boolean usesChronometer) {
        final long currentTimeMillis = System.currentTimeMillis();
        final String string = this.mContext.getString(2131296611);
        final String string2 = this.mContext.getString(2131296612);
        String contentText;
        if (usesChronometer) {
            contentText = this.mContext.getString(2131296613);
        }
        else {
            contentText = this.mContext.getString(2131296614);
        }
        final Notification build = new NotificationCompat$Builder(this.mContext).setOngoing(true).setVisibility(1).setOnlyAlertOnce(true).setCategory("call").setSmallIcon(2130837982).setLargeIcon(this.getLargeIcon()).setPriority(2).setContentTitle(string).setContentText(contentText).setTicker(string).setContentIntent(this.createNotificationPendingIntentResume()).setDeleteIntent(this.createNotificationPendingIntentDelete()).addAction(2130837739, string2, this.createNotificationPendingIntentDelete()).setAutoCancel(false).setWhen(currentTimeMillis).setUsesChronometer(usesChronometer).build();
        build.flags |= 0x40;
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
        return BitmapFactory.decodeResource(this.mContext.getResources(), 2130837940);
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
    
    void cancelNotification(final NetflixService netflixService, final Handler handler) {
        Log.d("nf_voip", "Cancel notification");
        this.mShowNotification.set(false);
        handler.post((Runnable)new CallNotificationManager$3(this, netflixService));
    }
    
    void showCallingNotification(final NetflixService netflixService, final Handler handler) {
        this.mShowNotification.set(true);
        handler.post((Runnable)new CallNotificationManager$1(this, netflixService, this.createNotification(false)));
    }
    
    void updateConnectedNotification(final NetflixService netflixService, final Handler handler) {
        this.mShowNotification.set(true);
        handler.post((Runnable)new CallNotificationManager$2(this, netflixService, this.createNotification(true)));
    }
}
