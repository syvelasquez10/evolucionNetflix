// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.ParcelUtils;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.NetflixService;

public class InfoEventHandler
{
    private static final long GCM_BROWSE_EVENT_RATE_LIMIT_DELAY_MS = 1000L;
    private static final long GCM_NOTIFICATION_LIST_CHANGE_EVENT_RATE_LIMIT_DELAY_MS = 1000L;
    private static final String TAG = "nf_push_info";
    private static InfoEventHandler mInfoEventHanlder;
    private static NetflixService mService;
    private final Runnable fetchPreAppDataRunnable;
    private final Runnable refreshCWRunnable;
    private final Runnable refreshIQRunnable;
    private final Runnable refreshLolomoRunnable;
    private final Runnable refreshSocialNotificationRunnable;
    
    static {
        InfoEventHandler.mInfoEventHanlder = new InfoEventHandler();
    }
    
    private InfoEventHandler() {
        this.refreshCWRunnable = new InfoEventHandler$1(this);
        this.refreshIQRunnable = new InfoEventHandler$2(this);
        this.refreshLolomoRunnable = new InfoEventHandler$3(this);
        this.refreshSocialNotificationRunnable = new InfoEventHandler$4(this);
        this.fetchPreAppDataRunnable = new InfoEventHandler$5(this);
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
    
    private void handleLolomoRefreshEvent(final NetflixService netflixService, final boolean b, final Payload payload) {
        if (!b) {
            this.informServerAndKillSelf(netflixService);
            return;
        }
        UserActionLogUtils.reportNewLolomoActionStarted(netflixService.getApplicationContext(), null, null);
        netflixService.getHandler().removeCallbacks(this.refreshLolomoRunnable);
        netflixService.getHandler().postDelayed(this.refreshLolomoRunnable, this.getBrowseEventRateLimitMs(netflixService));
        UserActionLogUtils.reportNewLolomoActionEnded(netflixService.getApplicationContext(), IClientLogging$CompletionReason.success, null, payload.renoCause, payload.renoMessageGuid, payload.renoCreationTimestamp, payload.messageGuid, payload.guid);
    }
    
    private void handleMyListEvent(final NetflixService netflixService, final boolean b, final boolean b2) {
        if (b) {
            netflixService.getHandler().removeCallbacks(this.refreshIQRunnable);
            netflixService.getHandler().postDelayed(this.refreshIQRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
        else if (b2) {
            netflixService.getHandler().removeCallbacks(this.fetchPreAppDataRunnable);
            netflixService.getHandler().postDelayed(this.fetchPreAppDataRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
    }
    
    private void handleNListChangeEvent(final NetflixService netflixService, final boolean b) {
        if (!b) {
            return;
        }
        Log.d("nf_push_info", "handling EVENT_NOTIFICATION_LIST_CHANGED");
        netflixService.getHandler().removeCallbacks(this.refreshSocialNotificationRunnable);
        netflixService.getHandler().postDelayed(this.refreshSocialNotificationRunnable, this.getNListChangeEventRateLimit(netflixService));
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
            netflixService.getHandler().removeCallbacks(this.fetchPreAppDataRunnable);
            netflixService.getHandler().postDelayed(this.fetchPreAppDataRunnable, this.getBrowseEventRateLimitMs(netflixService));
        }
    }
    
    private void informServerAndKillSelf(final NetflixService netflixService) {
        Log.d("nf_push_info", "Skip handling event - gcmInfoEvent woke up netflixService intent: ");
        netflixService.getPushNotification().informServiceStartedOnGcmInfo();
        if (this.isAccountReadyToSendReport(netflixService)) {
            Log.d("nf_push_info", "PushNotifiactionAgent already reported beacon onLogin - report and kill service");
            netflixService.getPushNotification().reportAndKillService();
            return;
        }
        Log.d("nf_push_info", "Account not ready, report later");
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
        final boolean boolean1 = ParcelUtils.readBoolean(intent, "isRunning");
        final boolean widgetInstalled = mService.isWidgetInstalled();
        if (!boolean1 && !widgetInstalled) {
            this.informServerAndKillSelf(mService);
            return;
        }
        if (Payload$ActionInfoType.isLolomoRefreshEvent(payload.actionInfoType)) {
            this.handleLolomoRefreshEvent(mService, boolean1, payload);
            return;
        }
        if (Payload$ActionInfoType.isMylistChangedEvent(payload.actionInfoType)) {
            this.handleMyListEvent(mService, boolean1, widgetInstalled);
            return;
        }
        if (Payload$ActionInfoType.isPlayEndEvent(payload.actionInfoType)) {
            this.handlePlayEndEvent(mService, boolean1, widgetInstalled);
            return;
        }
        if (Payload$ActionInfoType.isNotificationReadEvent(payload.actionInfoType)) {
            this.handleNReadEvent(mService, boolean1);
            return;
        }
        if (Payload$ActionInfoType.isNotificationListChangedEvent(payload.actionInfoType)) {
            this.handleNListChangeEvent(mService, boolean1);
            return;
        }
        Log.d("nf_push_info", String.format("unknown message - dropping", payload));
    }
}
