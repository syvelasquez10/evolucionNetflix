// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.logging.ads.model.AdvertiserIdRequest;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging;

public final class AdvertiserIdLoggingManager implements AdvertiserIdLogging
{
    private static final long DAY_IN_MS = 86400000L;
    private static final String TAG = "nf_adv_id";
    private String mAdIdReported;
    private Boolean mAdIdReportedOptedIn;
    private long mAdIdReportedTimestamp;
    private AdvertisingIdProvider mAdvertisingIdProvider;
    private Context mContext;
    private AdvertiserIdLoggingWebClient mLoggingWebClient;
    private LoggingAgent mOwner;
    private final BroadcastReceiver mReceiver;
    
    public AdvertiserIdLoggingManager(final Context mContext, final LoggingAgent mOwner) {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                if (Log.isLoggable("nf_adv_id", 2)) {
                    Log.v("nf_adv_id", "Received intent " + intent);
                }
                final String action = intent.getAction();
                if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN".equals(action)) {
                    Log.d("nf_adv_id", "onLogin");
                    AdvertiserIdLoggingManager.this.onLogin();
                }
                else {
                    if ("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT".equals(action)) {
                        Log.d("nf_adv_id", "onLogout");
                        AdvertiserIdLoggingManager.this.onLogout();
                        return;
                    }
                    if (Log.isLoggable("nf_adv_id", 3)) {
                        Log.d("nf_adv_id", "We do not support action " + action);
                    }
                }
            }
        };
        this.mContext = mContext;
        this.mOwner = mOwner;
        this.initProvider();
    }
    
    private void doSendAdvertiserId(final String s, final Boolean b, final EventType eventType) {
        this.mLoggingWebClient.sendLoggingEvent(new AdvertiserIdRequest(s, b, eventType).toJson(), new AdvertiserIdLoggingCallback() {
            @Override
            public void onFailure() {
                Log.d("nf_adv_id", "Advertiser ID failed to be delivered");
            }
            
            @Override
            public void onSuccess() {
                Log.d("nf_adv_id", "Advertiser ID delivered");
                final long currentTimeMillis = System.currentTimeMillis();
                PreferenceUtils.putStringPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id", s);
                PreferenceUtils.putLongPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id_ts", currentTimeMillis);
                PreferenceUtils.putBooleanPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id_opted_in", b);
                AdvertiserIdLoggingManager.this.mAdIdReported = s;
                AdvertiserIdLoggingManager.this.mAdIdReportedTimestamp = currentTimeMillis;
                AdvertiserIdLoggingManager.this.mAdIdReportedOptedIn = b;
            }
        });
    }
    
    private void initProvider() {
        if (!DeviceUtils.isFirstApplicationStartAfterInstallation(this.mContext)) {
            Log.d("nf_adv_id", "Not first start after installation");
            return;
        }
        Log.d("nf_adv_id", "First start after installation");
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                AdvertiserIdLoggingManager.this.mAdIdReported = PreferenceUtils.getStringPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id", null);
                AdvertiserIdLoggingManager.this.mAdIdReportedTimestamp = PreferenceUtils.getLongPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id_ts", 0L);
                AdvertiserIdLoggingManager.this.mAdIdReportedOptedIn = PreferenceUtils.getBooleanPref(AdvertiserIdLoggingManager.this.mContext, "advertisement_id_opted_in", false);
                AdvertiserIdLoggingManager.this.mAdvertisingIdProvider = AdvertisingIdProviderFactory.getInstance(AdvertiserIdLoggingManager.this.mContext);
                AdvertiserIdLoggingManager.this.sendAdvertiserId(EventType.install);
            }
        });
    }
    
    private boolean isAlreadySentInLastPeriod() {
        final long currentTimeMillis = System.currentTimeMillis();
        return this.mAdIdReportedTimestamp > 0L && currentTimeMillis < 86400000L + this.mAdIdReportedTimestamp;
    }
    
    private void onLogin() {
        this.sendAdvertiserId(EventType.sign_in);
    }
    
    private void onLogout() {
    }
    
    private void registerReceiver() {
        Log.d("nf_adv_id", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        intentFilter.setPriority(999);
        try {
            LocalBroadcastManager.getInstance(this.mContext).registerReceiver(this.mReceiver, intentFilter);
        }
        catch (Throwable t) {
            Log.e("nf_adv_id", "Failed to register ", t);
        }
    }
    
    private void unregisterReceiver() {
        try {
            LocalBroadcastManager.getInstance(this.mContext).unregisterReceiver(this.mReceiver);
        }
        catch (Throwable t) {
            Log.e("nf_adv_id", "Failed to unregister ", t);
        }
    }
    
    public void destroy() {
        Log.d("nf_adv_id", "Destroy and unregister receiver");
        this.unregisterReceiver();
    }
    
    @Override
    public AdverisingATrackingPreference getAdverisingTrackingPreference() {
        if (!this.isSupported()) {
            return null;
        }
        if (this.mAdvertisingIdProvider.isLimitAdTrackingEnabled()) {
            return AdverisingATrackingPreference.OPT_OUT;
        }
        return AdverisingATrackingPreference.OPT_IN;
    }
    
    @Override
    public String getAdvertiserId() {
        if (this.isSupported()) {
            return this.mAdvertisingIdProvider.getId();
        }
        return null;
    }
    
    public void init() {
        Log.d("nf_adv_id", "AdvertiserIdLoggingManager::init web client start ");
        this.mLoggingWebClient = AdvertiserIdLoggingWebClientFactory.create(this.mOwner.getResourceFetcher().getApiNextWebClient());
        this.registerReceiver();
        Log.d("nf_adv_id", "AdvertiserIdLoggingManager::init web client done ");
    }
    
    @Override
    public boolean isSupported() {
        return this.mAdvertisingIdProvider != null;
    }
    
    @Override
    public void sendAdvertiserId(final EventType eventType) {
        if (this.mAdvertisingIdProvider == null) {
            Log.d("nf_adv_id", "User is logged in, but ADV ID provider is not readu, postpone sending ID");
            return;
        }
        Log.d("nf_adv_id", "Ad ID provider is ready and request to send AD ID exist, execute.");
        final String id = this.mAdvertisingIdProvider.getId();
        final boolean b = !this.mAdvertisingIdProvider.isLimitAdTrackingEnabled();
        if (Log.isLoggable("nf_adv_id", 3)) {
            Log.d("nf_adv_id", "Ad ID provider exist, if we need to send ID " + id + ", opted in " + b);
        }
        if (id == null) {
            Log.e("nf_adv_id", "Ad id can not be null!");
            return;
        }
        if (eventType != EventType.check_in) {
            Log.d("nf_adv_id", "Not check in, execute");
            this.doSendAdvertiserId(id, b, eventType);
            return;
        }
        Log.d("nf_adv_id", "Check in, validate");
        if (this.mAdIdReported == null || !this.mAdIdReported.equals(this.mAdvertisingIdProvider.getId())) {
            Log.d("nf_adv_id", "Ad ID changed, execute");
            this.doSendAdvertiserId(id, b, eventType);
            return;
        }
        if (this.mAdIdReportedOptedIn == null || b != this.mAdIdReportedOptedIn) {
            Log.d("nf_adv_id", "opt in status changed, execute");
            this.doSendAdvertiserId(id, b, eventType);
            return;
        }
        Log.d("nf_adv_id", "Adverising ID is not changed, check when it was last time sent.");
        if (this.isAlreadySentInLastPeriod()) {
            Log.d("nf_adv_id", "Ad id and opt in status already sent in last 24 hours, do not send again");
            return;
        }
        Log.d("nf_adv_id", "Ad id and opt in status were NOT sent in last 24 hours, execute");
        this.doSendAdvertiserId(id, b, eventType);
    }
}
