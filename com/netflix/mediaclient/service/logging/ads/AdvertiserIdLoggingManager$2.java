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
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$EventType;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.Log;

class AdvertiserIdLoggingManager$2 implements AdvertiserIdLoggingCallback
{
    final /* synthetic */ AdvertiserIdLoggingManager this$0;
    final /* synthetic */ String val$id;
    final /* synthetic */ Boolean val$optedIn;
    
    AdvertiserIdLoggingManager$2(final AdvertiserIdLoggingManager this$0, final String val$id, final Boolean val$optedIn) {
        this.this$0 = this$0;
        this.val$id = val$id;
        this.val$optedIn = val$optedIn;
    }
    
    @Override
    public void onFailure() {
        Log.d("nf_adv_id", "Advertiser ID failed to be delivered");
    }
    
    @Override
    public void onSuccess() {
        Log.d("nf_adv_id", "Advertiser ID delivered");
        final long currentTimeMillis = System.currentTimeMillis();
        final NetflixPreference netflixPreference = new NetflixPreference(this.this$0.mContext);
        netflixPreference.putStringPref("advertisement_id", this.val$id);
        netflixPreference.putLongPref("advertisement_id_ts", currentTimeMillis);
        netflixPreference.putBooleanPref("advertisement_id_opted_in", (boolean)this.val$optedIn);
        netflixPreference.commit();
        this.this$0.mAdIdReported = this.val$id;
        this.this$0.mAdIdReportedTimestamp = currentTimeMillis;
        this.this$0.mAdIdReportedOptedIn = this.val$optedIn;
    }
}
