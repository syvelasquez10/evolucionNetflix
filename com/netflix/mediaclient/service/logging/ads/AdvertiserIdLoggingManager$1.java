// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$AdverisingATrackingPreference;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.ads.model.AdvertiserIdRequest;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$EventType;
import com.netflix.mediaclient.util.PreferenceUtils;

class AdvertiserIdLoggingManager$1 implements Runnable
{
    final /* synthetic */ AdvertiserIdLoggingManager this$0;
    
    AdvertiserIdLoggingManager$1(final AdvertiserIdLoggingManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.mAdIdReported = PreferenceUtils.getStringPref(this.this$0.mContext, "advertisement_id", null);
        this.this$0.mAdIdReportedTimestamp = PreferenceUtils.getLongPref(this.this$0.mContext, "advertisement_id_ts", 0L);
        this.this$0.mAdIdReportedOptedIn = PreferenceUtils.getBooleanPref(this.this$0.mContext, "advertisement_id_opted_in", false);
        this.this$0.mAdvertisingIdProvider = AdvertisingIdProviderFactory.getInstance(this.this$0.mContext);
        final AdvertiserIdLogging$EventType access$500 = this.this$0.mPostponedEvent;
        this.this$0.mPostponedEvent = null;
        if (!DeviceUtils.isFirstApplicationStartAfterInstallation(this.this$0.mContext)) {
            Log.d("nf_adv_id", "Not first start after installation");
        }
        else {
            Log.d("nf_adv_id", "First start after installation");
            this.this$0.sendAdvertiserId(AdvertiserIdLogging$EventType.install);
        }
        if (access$500 != null) {
            this.this$0.sendAdvertiserId(access$500);
        }
    }
}
