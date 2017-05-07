// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.ads;

import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$AdverisingATrackingPreference;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.ads.model.AdvertiserIdRequest;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.servicemgr.AdvertiserIdLogging$EventType;
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
    private AdvertiserIdLogging$EventType mPostponedEvent;
    private final BroadcastReceiver mReceiver;
    
    public AdvertiserIdLoggingManager(final Context mContext, final LoggingAgent mOwner) {
        this.mReceiver = new AdvertiserIdLoggingManager$3(this);
        this.mContext = mContext;
        this.mOwner = mOwner;
        this.initProvider();
    }
    
    private void doSendAdvertiserId(final String s, final Boolean b, final AdvertiserIdLogging$EventType advertiserIdLogging$EventType) {
        String deviceModel;
        final String s2 = deviceModel = null;
        if (this.mOwner != null) {
            deviceModel = s2;
            if (this.mOwner.getConfigurationAgent() != null) {
                deviceModel = s2;
                if (this.mOwner.getConfigurationAgent().getEsnProvider() != null) {
                    deviceModel = this.mOwner.getConfigurationAgent().getEsnProvider().getDeviceModel();
                }
            }
        }
        this.mLoggingWebClient.sendLoggingEvent(new AdvertiserIdRequest(s, b, advertiserIdLogging$EventType, deviceModel).toJson(), new AdvertiserIdLoggingManager$2(this, s, b));
    }
    
    private void initProvider() {
        new BackgroundTask().execute(new AdvertiserIdLoggingManager$1(this));
    }
    
    private boolean isAlreadySentInLastPeriod() {
        final long currentTimeMillis = System.currentTimeMillis();
        return this.mAdIdReportedTimestamp > 0L && currentTimeMillis < 86400000L + this.mAdIdReportedTimestamp;
    }
    
    private void onLogin() {
        this.sendAdvertiserId(AdvertiserIdLogging$EventType.sign_in);
    }
    
    private void onLogout() {
    }
    
    private void registerReceiver() {
        Log.d("nf_adv_id", "Register receiver");
        final IntentFilter intentFilter = new IntentFilter("com.netflix.mediaclient.intent.action.PUSH_ONLOGIN");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.PUSH_ONLOGOUT");
        intentFilter.addAction("com.netflix.mediaclient.intent.action.ONSIGNUP");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        intentFilter.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
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
    public AdvertiserIdLogging$AdverisingATrackingPreference getAdverisingTrackingPreference() {
        if (!this.isSupported()) {
            return null;
        }
        if (this.mAdvertisingIdProvider.isLimitAdTrackingEnabled()) {
            return AdvertiserIdLogging$AdverisingATrackingPreference.OPT_OUT;
        }
        return AdvertiserIdLogging$AdverisingATrackingPreference.OPT_IN;
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
    public void sendAdvertiserId(final AdvertiserIdLogging$EventType mPostponedEvent) {
        while (true) {
            String id = null;
            boolean b = false;
        Label_0135:
            while (true) {
                Label_0130: {
                    synchronized (this) {
                        if (this.mAdvertisingIdProvider == null) {
                            Log.d("nf_adv_id", "User is logged in, but ADV ID provider is not readu, postpone sending ID");
                            this.mPostponedEvent = mPostponedEvent;
                        }
                        else {
                            this.mPostponedEvent = null;
                            Log.d("nf_adv_id", "Ad ID provider is ready and request to send AD ID exist, execute.");
                            id = this.mAdvertisingIdProvider.getId();
                            if (this.mAdvertisingIdProvider.isLimitAdTrackingEnabled()) {
                                break Label_0130;
                            }
                            b = true;
                            if (Log.isLoggable("nf_adv_id", 3)) {
                                Log.d("nf_adv_id", "Ad ID provider exist, if we need to send ID " + id + ", opted in " + b);
                            }
                            if (id != null) {
                                break Label_0135;
                            }
                            Log.e("nf_adv_id", "Ad id can not be null!");
                        }
                        return;
                    }
                }
                b = false;
                continue;
            }
            final AdvertiserIdLogging$EventType advertiserIdLogging$EventType;
            if (advertiserIdLogging$EventType != AdvertiserIdLogging$EventType.check_in) {
                Log.d("nf_adv_id", "Not check in, execute");
                this.doSendAdvertiserId(id, b, advertiserIdLogging$EventType);
                return;
            }
            Log.d("nf_adv_id", "Check in, validate");
            if (this.mAdIdReported == null || !this.mAdIdReported.equals(this.mAdvertisingIdProvider.getId())) {
                Log.d("nf_adv_id", "Ad ID changed, execute");
                this.doSendAdvertiserId(id, b, advertiserIdLogging$EventType);
                return;
            }
            if (this.mAdIdReportedOptedIn == null || b != this.mAdIdReportedOptedIn) {
                Log.d("nf_adv_id", "opt in status changed, execute");
                this.doSendAdvertiserId(id, b, advertiserIdLogging$EventType);
                return;
            }
            Log.d("nf_adv_id", "Adverising ID is not changed, check when it was last time sent.");
            if (this.isAlreadySentInLastPeriod()) {
                Log.d("nf_adv_id", "Ad id and opt in status already sent in last 24 hours, do not send again");
                return;
            }
            Log.d("nf_adv_id", "Ad id and opt in status were NOT sent in last 24 hours, execute");
            this.doSendAdvertiserId(id, b, advertiserIdLogging$EventType);
        }
    }
}
