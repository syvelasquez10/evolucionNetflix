// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.ParcelUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;

public class InfoEventHandler
{
    private static final long GCM_BROWSE_EVENT_RATE_LIMIT_DELAY_MS = 1000L;
    private static final long GCM_NOTIFICATION_LIST_CHANGE_EVENT_RATE_LIMIT_DELAY_MS = 1000L;
    private static final String TAG = "nf_push_info";
    private static InfoEventHandler mInfoEventHanlder;
    private static NetflixService mService;
    private final Runnable refreshAllRunnable;
    private final Runnable refreshCWRunnable;
    private final Runnable refreshIQRunnable;
    private final Runnable refreshSocialNotificationRunnable;
    
    static {
        InfoEventHandler.mInfoEventHanlder = new InfoEventHandler();
    }
    
    private InfoEventHandler() {
        this.refreshCWRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("nf_push_info", "Refreshing CW via runnable");
                if (InfoEventHandler.mService != null) {
                    InfoEventHandler.mService.getBrowse().refreshCW();
                }
            }
        };
        this.refreshIQRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("nf_push_info", "Refreshing IQ via runnable");
                if (InfoEventHandler.mService != null) {
                    InfoEventHandler.mService.getBrowse().refreshIQ();
                }
            }
        };
        this.refreshAllRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("nf_push_info", "Refreshing ALL lolomo via runnable");
                if (InfoEventHandler.mService != null) {
                    InfoEventHandler.mService.getBrowse().refreshAll();
                }
            }
        };
        this.refreshSocialNotificationRunnable = new Runnable() {
            @Override
            public void run() {
                Log.i("nf_push_info", "Refreshing socialNotifications via runnable");
                if (InfoEventHandler.mService != null) {
                    InfoEventHandler.mService.getBrowse().refreshSocialNotifications(true, false, null);
                }
            }
        };
    }
    
    private long getBrowseEventRateLimitMs(final NetflixService netflixService) {
        final int rateLimitForGcmBrowseEvents = netflixService.getConfiguration().getRateLimitForGcmBrowseEvents();
        if (rateLimitForGcmBrowseEvents < 0) {
            return 0L;
        }
        if (rateLimitForGcmBrowseEvents > 0) {
            return rateLimitForGcmBrowseEvents * 1000;
        }
        return 1000L;
    }
    
    public static InfoEventHandler getInstance() {
        return InfoEventHandler.mInfoEventHanlder;
    }
    
    private long getNListChangeEventRateLimit(final NetflixService netflixService) {
        final int rateLimitForNListChangeEvents = netflixService.getConfiguration().getRateLimitForNListChangeEvents();
        if (rateLimitForNListChangeEvents < 0) {
            return 0L;
        }
        if (rateLimitForNListChangeEvents > 0) {
            return rateLimitForNListChangeEvents * 1000;
        }
        return 1000L;
    }
    
    private void handleMyListEvent(final NetflixService netflixService, final boolean b, final boolean b2) {
        if (b) {
            netflixService.getHandler().removeCallbacks(this.refreshIQRunnable);
            netflixService.getHandler().postDelayed(this.refreshIQRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
        else if (b2) {
            netflixService.getHandler().removeCallbacks(this.refreshAllRunnable);
            netflixService.getHandler().postDelayed(this.refreshAllRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
    }
    
    private void handleNListChangeEvent(final NetflixService netflixService, final boolean b) {
        if (!b) {
            return;
        }
        Log.d("nf_push_info", "handling EVENT_NOTIFICATION_LIST_CHANGED");
        if (!netflixService.getPushNotification().isOptIn()) {
            netflixService.getHandler().removeCallbacks(this.refreshSocialNotificationRunnable);
            netflixService.getHandler().postDelayed(this.refreshSocialNotificationRunnable, this.getNListChangeEventRateLimit(netflixService));
            return;
        }
        Log.d("nf_push_info", "dropping EVENT_NOTIFICATION_LIST_CHANGED invisible message - userOptedIn");
    }
    
    private void handleNReadEvent(final NetflixService netflixService, final boolean b) {
        if (!b) {
            return;
        }
        Log.d("nf_push_info", "handling EVENT_NOTIFICATION_READ");
        netflixService.getHandler().removeCallbacks(this.refreshSocialNotificationRunnable);
        netflixService.getHandler().postDelayed(this.refreshSocialNotificationRunnable, this.getNListChangeEventRateLimit(netflixService));
    }
    
    private void handlePlayEndEvent(final NetflixService netflixService, final boolean b, final boolean b2) {
        if (b) {
            netflixService.getHandler().removeCallbacks(this.refreshCWRunnable);
            netflixService.getHandler().postDelayed(this.refreshCWRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
        else if (b2) {
            netflixService.getHandler().removeCallbacks(this.refreshAllRunnable);
            netflixService.getHandler().postDelayed(this.refreshAllRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
    }
    
    private void informServerAndKillSelf(final NetflixService netflixService) {
        Log.d("nf_push_info", "Skip handling event - gcmInfoEvent woke up netflixService intent: ");
        if (this.isAccountReadyToSendReport(netflixService)) {
            Log.d("nf_push_info", "PushNotifiactionAgent already reported beacon onLogin - report gcmOptOut=false and kill service");
            netflixService.getPushNotification().reportAndKillService();
            return;
        }
        Log.d("nf_push_info", "PushNotificationAgent will report gcmInfoOpt: false");
        netflixService.getPushNotification().informServiceStartedOnGcmInfo();
    }
    
    private boolean isAccountReadyToSendReport(final NetflixService netflixService) {
        return netflixService.getCurrentProfile() != null;
    }
    
    public void handleEvent(final NetflixService mService, final Payload payload, final Intent intent) {
        if (mService == null) {
            Log.d("nf_push_info", "not handling event service is null for payload: " + payload);
            return;
        }
        InfoEventHandler.mService = mService;
        final String profileGuid = mService.getCurrentProfile().getProfileGuid();
        Log.d("nf_push_info", String.format("rcvd payload: %s", payload));
        if (StringUtils.isNotEmpty(payload.profileGuid)) {
            if (!StringUtils.safeEquals(profileGuid, payload.profileGuid)) {
                Log.d("nf_push_info", String.format("drop push event - !currentProfile :%s", profileGuid));
                return;
            }
        }
        else {
            Log.d("nf_push_info", "msg missing profileGuid... processing");
        }
        final boolean boolean1 = ParcelUtils.readBoolean(intent, "isRunning");
        final boolean widgetInstalled = mService.isWidgetInstalled();
        if (!boolean1 && !widgetInstalled) {
            this.informServerAndKillSelf(mService);
            return;
        }
        if (Payload.ActionInfoType.isMylistChangedEvent(payload.actionInfoType)) {
            this.handleMyListEvent(mService, boolean1, widgetInstalled);
            return;
        }
        if (Payload.ActionInfoType.isPlayEndEvent(payload.actionInfoType)) {
            this.handlePlayEndEvent(mService, boolean1, widgetInstalled);
            return;
        }
        if (Payload.ActionInfoType.isNotificationReadEvent(payload.actionInfoType)) {
            this.handleNReadEvent(mService, boolean1);
            return;
        }
        if (Payload.ActionInfoType.isNotificationListChangedEvent(payload.actionInfoType)) {
            this.handleNListChangeEvent(mService, boolean1);
            return;
        }
        Log.d("nf_push_info", String.format("unknown message - dropping", payload));
    }
}
