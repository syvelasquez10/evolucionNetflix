// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.preappservice.PDiskDataRepository;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.preappservice.PService;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;

public class PreAppAgentDataHandler
{
    private static final int PREAPP_MAX_VIDEOS_IN_LIST = 5;
    private static final String TAG = "nf_preapp_datahandler";
    private static Context mContext;
    private static ServiceAgent mServiceAgent;
    
    public PreAppAgentDataHandler(final Context mContext, final ServiceAgent mServiceAgent) {
        PreAppAgentDataHandler.mContext = mContext;
        PreAppAgentDataHandler.mServiceAgent = mServiceAgent;
    }
    
    private void fetchUrl(final String s, final String s2, final LoggingResourceFetcherCallback loggingResourceFetcherCallback) {
        if (s2 != null) {
            PreAppAgentDataHandler.mServiceAgent.getResourceFetcher().fetchResource(s2, IClientLogging$AssetType.boxArt, loggingResourceFetcherCallback);
            return;
        }
        Log.d("nf_preapp_datahandler", String.format("video.id: %s, getHorzDispUrl() is null", s));
    }
    
    private void notifyOthers(final Context context, final UpdateEventType updateEventType) {
        Log.d("nf_preapp_datahandler", "notifyOthers updateType:" + updateEventType);
        if (!PreAppAgentDataHandler.mServiceAgent.getPreAppAgent().isWidgetInstalled() && !PService.isRemoteUiDevice() && !PService.WIDGET_ENABLED_FOR_TEST) {
            Log.d("nf_preapp_datahandler", "skipNotiying others - !widgetInstalled & !isRemoteUiDevice & !TestCode");
        }
        else {
            final String s = null;
            String s2 = null;
            switch (PreAppAgentDataHandler$4.$SwitchMap$com$netflix$mediaclient$service$preapp$UpdateEventType[updateEventType.ordinal()]) {
                default: {
                    Log.d("nf_preapp_datahandler", "unknown event type received");
                    s2 = s;
                    break;
                }
                case 1: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED";
                    break;
                }
                case 2: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED";
                    break;
                }
                case 3: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_IQ_UPDATED";
                    break;
                }
            }
            if (!StringUtils.isEmpty(s2)) {
                final Intent intent = new Intent(s2);
                intent.addCategory("com.netflix.mediaclient.intent.category.PREAPP_AGENT_FROM_CATEGORY");
                intent.setClass(context, (Class)PService.class);
                if (Log.isLoggable("nf_preapp_datahandler", 3)) {
                    Log.d("nf_preapp_datahandler", String.format("sending intent: %s", intent));
                }
                context.startService(intent);
            }
        }
    }
    
    private void proceedAfterFetchOfImages(final PreAppAgentDataHandler$DataToDisk preAppAgentDataHandler$DataToDisk, final UpdateEventType updateEventType) {
        final int urlFetchCount = preAppAgentDataHandler$DataToDisk.getUrlFetchCount(updateEventType);
        final PreAppAgentDataHandler$1 preAppAgentDataHandler$1 = new PreAppAgentDataHandler$1(this, urlFetchCount, preAppAgentDataHandler$DataToDisk, updateEventType);
        if (urlFetchCount <= 0) {
            this.storeAndNotify(preAppAgentDataHandler$DataToDisk, updateEventType);
        }
        else {
            if (UpdateEventType.isBBUpdated(updateEventType) && preAppAgentDataHandler$DataToDisk.billboards != null) {
                for (final Billboard billboard : preAppAgentDataHandler$DataToDisk.billboards) {
                    this.fetchUrl(billboard.getId(), billboard.getHorzDispUrl(), preAppAgentDataHandler$1);
                }
            }
            if (UpdateEventType.isCWUpdated(updateEventType) && preAppAgentDataHandler$DataToDisk.cwVideos != null) {
                for (final CWVideo cwVideo : preAppAgentDataHandler$DataToDisk.cwVideos) {
                    this.fetchUrl(cwVideo.getId(), cwVideo.getHorzDispUrl(), preAppAgentDataHandler$1);
                }
            }
            if (UpdateEventType.isIQUpdated(updateEventType) && preAppAgentDataHandler$DataToDisk.iqVideos != null) {
                for (final Video video : preAppAgentDataHandler$DataToDisk.iqVideos) {
                    this.fetchUrl(video.getId(), video.getHorzDispUrl(), preAppAgentDataHandler$1);
                }
            }
            if (UpdateEventType.isRecoListUpdated(updateEventType) && preAppAgentDataHandler$DataToDisk.recoVideos != null) {
                for (final Video video2 : preAppAgentDataHandler$DataToDisk.recoVideos) {
                    this.fetchUrl(video2.getId(), video2.getHorzDispUrl(), preAppAgentDataHandler$1);
                }
            }
        }
    }
    
    private void storeAndNotify(final PreAppAgentDataHandler$DataToDisk preAppAgentDataHandler$DataToDisk, final UpdateEventType updateEventType) {
        new BackgroundTask().execute(new PreAppAgentDataHandler$3(this, preAppAgentDataHandler$DataToDisk, new PreAppAgentDataHandler$2(this, updateEventType)));
    }
    
    public void update(final UpdateEventType updateEventType) {
        final ServiceAgent$BrowseAgentInterface browseAgent = PreAppAgentDataHandler.mServiceAgent.getBrowseAgent();
        final PreAppAgentDataHandler$DataToDisk preAppAgentDataHandler$DataToDisk = new PreAppAgentDataHandler$DataToDisk(this);
        preAppAgentDataHandler$DataToDisk.billboards = browseAgent.fetchBillboardsFromCache(5);
        preAppAgentDataHandler$DataToDisk.cwVideos = browseAgent.fetchCWFromCache(5);
        preAppAgentDataHandler$DataToDisk.iqVideos = browseAgent.fetchIQFromCache(5);
        preAppAgentDataHandler$DataToDisk.recoVideos = browseAgent.fetchRecommendedListFromCache(5);
        PDiskDataRepository.clearDiskData(PreAppAgentDataHandler.mContext);
        this.proceedAfterFetchOfImages(preAppAgentDataHandler$DataToDisk, updateEventType);
    }
}
