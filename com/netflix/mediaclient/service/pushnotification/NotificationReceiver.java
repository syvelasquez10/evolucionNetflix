// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.SocialNotificationsUtils;
import android.app.NotificationManager;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationsActivity;
import com.netflix.mediaclient.ui.details.ShowDetailsActivity;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.details.MovieDetailsActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class NotificationReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_push";
    
    private Intent createIntentForPushNotificationAgent(final Context context, final Intent intent, final String s) {
        final Intent intent2 = new Intent(s);
        intent2.setClass(context, (Class)NetflixService.class);
        intent2.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        final String stringExtra = intent.getStringExtra("swiped_social_notification_id");
        if (!StringUtils.isEmpty(stringExtra)) {
            intent2.putExtra("swiped_social_notification_id", stringExtra);
        }
        MessageData.addMessageDataToIntent(intent2, MessageData.createInstance(intent));
        return intent2;
    }
    
    private void handleBrowserRedirectNotification(final Context context, final Intent intent) {
        Log.d("nf_push", "received notification browser redirect");
        final Intent intentForPushNotificationAgent = this.createIntentForPushNotificationAgent(context, intent, "com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT");
        if (intentForPushNotificationAgent != null) {
            final String stringExtra = intent.getStringExtra("target_url");
            if (stringExtra != null) {
                intentForPushNotificationAgent.putExtra("target_url", stringExtra);
            }
            context.startService(intentForPushNotificationAgent);
        }
    }
    
    private void handleCanceledNotification(final Context context, Intent intentForPushNotificationAgent) {
        Log.d("nf_push", "received notification canceled");
        intentForPushNotificationAgent = this.createIntentForPushNotificationAgent(context, intentForPushNotificationAgent, "com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
        if (intentForPushNotificationAgent != null) {
            context.startService(intentForPushNotificationAgent);
        }
    }
    
    private void handleMdp(final Context context, final Intent intent) {
        Log.d("nf_push", "received show MDP from notificaton");
        removeSocialNotification(context, intent);
        intent.setClass(context, (Class)MovieDetailsActivity.class);
        intent.addFlags(872415232);
        context.startActivity(intent);
    }
    
    private void handlePlay(final Context context, final Intent intent) {
        Log.d("nf_push", "received play from notification");
        removeSocialNotification(context, intent);
        intent.setClass(context, (Class)PlayerActivity.class);
        intent.addFlags(872415232);
        context.startActivity(intent);
    }
    
    private void handleSayThankYoy(final Context context, final Intent intent) {
        Log.d("nf_push", "received Say Thank You from notificaton");
        removeSocialNotification(context, intent);
        intent.setClass(context, (Class)NetflixService.class);
        intent.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        context.startService(intent);
    }
    
    private void handleSdp(final Context context, final Intent intent) {
        Log.d("nf_push", "received show SDP from notificaton");
        removeSocialNotification(context, intent);
        intent.setClass(context, (Class)ShowDetailsActivity.class);
        intent.addFlags(872415232);
        context.startActivity(intent);
    }
    
    private void handleSocial(final Context context, final Intent intent) {
        Log.d("nf_push", "received social list from notificaton");
        removeSocialNotification(context, intent);
        intent.setClass(context, (Class)SocialNotificationsActivity.class);
        intent.addFlags(872415232);
        context.startActivity(intent);
    }
    
    private static void removeSocialNotification(final Context context, final Intent intent) {
        context.sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        ((NotificationManager)context.getSystemService("notification")).cancel(1000);
        SocialNotificationsUtils.ifSocialNotificationWasCanceledUpdateItsStatus(context, intent, "nf_push");
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", intent);
            Log.d("nf_push", "Received an action: " + action);
        }
        switch (action) {
            default: {
                Log.d("nf_push", "Not supported!");
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED": {
                this.handleCanceledNotification(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT": {
                this.handleBrowserRedirectNotification(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_SAY_THANKS": {
                this.handleSayThankYoy(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_SOCIAL": {
                this.handleSocial(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_MOVIE_DETAILS": {
                this.handleMdp(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_SHOW_DETAILS": {
                this.handleSdp(context, intent);
            }
            case "com.netflix.mediaclient.intent.action.NOTIFICATION_PLAY": {
                this.handlePlay(context, intent);
            }
        }
    }
}
