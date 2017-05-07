// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.IntentUtils;
import android.net.Uri;
import com.netflix.mediaclient.util.IrisUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.configuration.SettingsConfiguration;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.google.android.gcm.GCMRegistrar;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.logging.UserData;
import java.util.Map;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.IPushNotification;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PushNotificationAgent$3 extends BroadcastReceiver
{
    final /* synthetic */ PushNotificationAgent this$0;
    
    PushNotificationAgent$3(final PushNotificationAgent this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v("nf_push", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN".equals(action)) {
            Log.d("nf_push", "onLogin");
            this.this$0.onLogin();
        }
        else {
            if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT".equals(action)) {
                Log.d("nf_push", "onLogout");
                this.this$0.onLogout(this.this$0.createUserData(intent));
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTIN".equals(action)) {
                Log.d("nf_push", "optIn");
                this.this$0.onNotificationOptIn(true);
                return;
            }
            if ("com.netflix.mediaclient.intent.action.PUSH_NOTIFICATION_OPTOUT".equals(action)) {
                Log.d("nf_push", "optOut");
                this.this$0.onNotificationOptIn(false);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_push", "We do not support action " + action);
            }
        }
    }
}
