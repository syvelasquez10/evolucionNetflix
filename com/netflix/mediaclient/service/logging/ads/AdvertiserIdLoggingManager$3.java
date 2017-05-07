// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$AdverisingATrackingPreference;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.ads.model.AdvertiserIdRequest;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$EventType;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class AdvertiserIdLoggingManager$3 extends BroadcastReceiver
{
    final /* synthetic */ AdvertiserIdLoggingManager this$0;
    
    AdvertiserIdLoggingManager$3(final AdvertiserIdLoggingManager this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v("nf_adv_id", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN".equals(action)) {
            Log.d("nf_adv_id", "onLogin");
            this.this$0.onLogin();
        }
        else {
            if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT".equals(action)) {
                Log.d("nf_adv_id", "onLogout");
                this.this$0.onLogout();
                return;
            }
            if ("com.netflix.mediaclient.intent.action.ONSIGNUP".equals(action)) {
                Log.d("nf_adv_id", "onSignUp");
                this.this$0.sendAdvertiserId(AdvertiserIdLogging$EventType.sign_up);
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_adv_id", "We do not support action " + action);
            }
        }
    }
}
