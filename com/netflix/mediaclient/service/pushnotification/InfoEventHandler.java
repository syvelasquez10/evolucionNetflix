// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ParcelUtils;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.service.NetflixService;

public class InfoEventHandler
{
    private static final long GCM_BROWSE_EVENT_RATE_LIMIT_DELAY_MS = 1000L;
    private static final String TAG = "nf_push_info";
    private static InfoEventHandler mInfoEventHanlder;
    private static long mPrevMyListEventTime;
    private static long mPrevPlayEndEventTime;
    
    static {
        InfoEventHandler.mInfoEventHanlder = new InfoEventHandler();
    }
    
    private long getBrowseEventRateLimit(final NetflixService netflixService) {
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
    
    private boolean isAccountReadyToSendReport(final NetflixService netflixService) {
        return netflixService.getCurrentProfile() != null;
    }
    
    public void handleEvent(final NetflixService netflixService, final Payload payload, final Intent intent) {
        if (netflixService == null) {
            Log.d("nf_push_info", "not handling event service is null for payload: " + payload);
        }
        else {
            final String profileId = netflixService.getCurrentProfile().getProfileId();
            Log.d("nf_push_info", String.format("rcvd payload: %s", payload));
            if (!ParcelUtils.readBoolean(intent, "isRunning")) {
                Log.d("nf_push_info", String.format("Skip handling event - gcmInfoEvent woke up netflixService intent: %s", intent));
                if (this.isAccountReadyToSendReport(netflixService)) {
                    Log.d("nf_push_info", "PushNotifiactionAgent already reported beacon onLogin - report gcmOptOut=false and kill service");
                    netflixService.getPushNotification().reportAndKillService();
                    return;
                }
                Log.d("nf_push_info", "PushNotificationAgent will report gcmInfoOpt: false");
                netflixService.getPushNotification().informServiceStartedOnGcmInfo();
            }
            else {
                if (StringUtils.isNotEmpty(payload.profileGuid)) {
                    if (!StringUtils.safeEquals(profileId, payload.profileGuid)) {
                        Log.d("nf_push_info", String.format("drop push event - !currentProfile :%s", profileId));
                        return;
                    }
                }
                else {
                    Log.d("nf_push_info", "msg missing profileGuid... processing");
                }
                if (!Payload.ActionInfoType.EVENT_MYLIST_CHANGED.getValue().equals(payload.actionInfoType) && !Payload.ActionInfoType.EVENT_PLAYBACK_ENDED.getValue().equals(payload.actionInfoType)) {
                    Log.d("nf_push_info", String.format("drop push event - !actionInfoType :%s", payload.actionInfoType));
                    return;
                }
                if (Payload.ActionInfoType.EVENT_MYLIST_CHANGED.getValue().equals(payload.actionInfoType)) {
                    if (System.currentTimeMillis() - InfoEventHandler.mPrevMyListEventTime < this.getBrowseEventRateLimit(netflixService)) {
                        Log.d("nf_push_info", String.format("drop push event - rate limiting lastMylistEvent: %d, currentTime: %d", InfoEventHandler.mPrevMyListEventTime, System.currentTimeMillis()));
                        return;
                    }
                    InfoEventHandler.mPrevMyListEventTime = System.currentTimeMillis();
                    netflixService.getBrowse().refreshIQ();
                }
                if (Payload.ActionInfoType.EVENT_PLAYBACK_ENDED.getValue().equals(payload.actionInfoType)) {
                    if (System.currentTimeMillis() - InfoEventHandler.mPrevPlayEndEventTime < this.getBrowseEventRateLimit(netflixService)) {
                        Log.d("nf_push_info", String.format("drop push event - rate limiting mPrevPlayEndEventTime: %d, currentTime: %d", InfoEventHandler.mPrevPlayEndEventTime, System.currentTimeMillis()));
                        return;
                    }
                    InfoEventHandler.mPrevPlayEndEventTime = System.currentTimeMillis();
                    netflixService.getBrowse().refreshCW();
                }
            }
        }
    }
}
