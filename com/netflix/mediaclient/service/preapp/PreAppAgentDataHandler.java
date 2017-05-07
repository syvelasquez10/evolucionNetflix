// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.netflix.mediaclient.service.preappservice.PDiskDataRepository;
import com.netflix.mediaclient.util.data.DataRepository;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import com.netflix.mediaclient.android.app.Status;
import android.content.Intent;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.preappservice.PService;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging;
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
            PreAppAgentDataHandler.mServiceAgent.getResourceFetcher().fetchResource(s2, IClientLogging.AssetType.boxArt, loggingResourceFetcherCallback);
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
            switch (updateEventType) {
                default: {
                    Log.d("nf_preapp_datahandler", "unknown event type received");
                    s2 = s;
                    break;
                }
                case ALL_UPDATED: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ALL_UPDATED";
                    break;
                }
                case CW_UPDATED: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_CW_UPDATED";
                    break;
                }
                case IQ_UPDATED: {
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
    
    private void proceedAfterFetchOfImages(final DataToDisk dataToDisk, final UpdateEventType updateEventType) {
        final int urlFetchCount = dataToDisk.getUrlFetchCount(updateEventType);
        final LoggingResourceFetcherCallback loggingResourceFetcherCallback = new LoggingResourceFetcherCallback() {
            private int mCount = urlFetchCount;
            
            @Override
            public void onResourceFetched(final String s, final String s2, final Status status) {
                Log.d("nf_preapp_datahandler", String.format("onResourceFetched mCount: %d, reqUrl: %s, localUrl: %s", this.mCount, s, s2));
                dataToDisk.urlMap.put(s, s2);
                final int mCount = this.mCount - 1;
                this.mCount = mCount;
                if (mCount <= 0) {
                    PreAppAgentDataHandler.this.storeAndNotify(dataToDisk, updateEventType);
                }
            }
        };
        if (urlFetchCount <= 0) {
            this.storeAndNotify(dataToDisk, updateEventType);
        }
        else {
            if (UpdateEventType.isBBUpdated(updateEventType) && dataToDisk.billboards != null) {
                for (final Billboard billboard : dataToDisk.billboards) {
                    this.fetchUrl(billboard.getId(), billboard.getHorzDispUrl(), loggingResourceFetcherCallback);
                }
            }
            if (UpdateEventType.isCWUpdated(updateEventType) && dataToDisk.cwVideos != null) {
                for (final CWVideo cwVideo : dataToDisk.cwVideos) {
                    this.fetchUrl(cwVideo.getId(), cwVideo.getHorzDispUrl(), loggingResourceFetcherCallback);
                }
            }
            if (UpdateEventType.isIQUpdated(updateEventType) && dataToDisk.iqVideos != null) {
                for (final Video video : dataToDisk.iqVideos) {
                    this.fetchUrl(video.getId(), video.getHorzDispUrl(), loggingResourceFetcherCallback);
                }
            }
            if (UpdateEventType.isRecoListUpdated(updateEventType) && dataToDisk.recoVideos != null) {
                for (final Video video2 : dataToDisk.recoVideos) {
                    this.fetchUrl(video2.getId(), video2.getHorzDispUrl(), loggingResourceFetcherCallback);
                }
            }
        }
    }
    
    private void storeAndNotify(final DataToDisk dataToDisk, final UpdateEventType updateEventType) {
        new BackgroundTask().execute(new Runnable() {
            final /* synthetic */ DataRepository.DataSavedCallback val$callback = new DataRepository.DataSavedCallback(this, updateEventType) {
                final /* synthetic */ UpdateEventType val$updateType;
                
                @Override
                public void onDataSaved(final String s) {
                    Log.d("nf_preapp_datahandler", "onDataSaved");
                    PreAppAgentDataHandler.mServiceAgent.getMainHandler().post((Runnable)new Runnable(this) {
                        @Override
                        public void run() {
                            PreAppAgentDataHandler.this.notifyOthers(PreAppAgentDataHandler.mContext, DataSavedCallback.this.val$updateType);
                        }
                    });
                }
            };
            
            @Override
            public void run() {
                PDiskDataRepository.saveData(PreAppAgentDataHandler.mContext, dataToDisk.toJsonString(), this.val$callback);
            }
        });
    }
    
    public void update(final UpdateEventType updateEventType) {
        final ServiceAgent.BrowseAgentInterface browseAgent = PreAppAgentDataHandler.mServiceAgent.getBrowseAgent();
        final DataToDisk dataToDisk = new DataToDisk();
        dataToDisk.billboards = browseAgent.fetchBillboardsFromCache(5);
        dataToDisk.cwVideos = browseAgent.fetchCWFromCache(5);
        dataToDisk.iqVideos = browseAgent.fetchIQFromCache(5);
        dataToDisk.recoVideos = browseAgent.fetchRecommendedListFromCache(5);
        PDiskDataRepository.clearDiskData(PreAppAgentDataHandler.mContext);
        this.proceedAfterFetchOfImages(dataToDisk, updateEventType);
    }
    
    private class DataToDisk
    {
        @SerializedName("billboardList")
        public List<Billboard> billboards;
        @SerializedName("continueWatchingList")
        public List<CWVideo> cwVideos;
        @SerializedName("iqList")
        public List<Video> iqVideos;
        @SerializedName("recoList")
        List<Video> recoVideos;
        @SerializedName("urlMap")
        public Map<String, String> urlMap;
        
        DataToDisk() {
            this.urlMap = new HashMap<String, String>();
        }
        
        public int getUrlFetchCount(final UpdateEventType updateEventType) {
            final int n = 0;
            int n2 = 0;
            int n3 = n;
            if (UpdateEventType.isBBUpdated(updateEventType)) {
                n3 = n;
                if (this.billboards != null) {
                    final Iterator<Billboard> iterator = this.billboards.iterator();
                    while (true) {
                        n3 = n2;
                        if (!iterator.hasNext()) {
                            break;
                        }
                        if (iterator.next().getHorzDispUrl() == null) {
                            continue;
                        }
                        ++n2;
                    }
                }
            }
            int n4 = n3;
            if (UpdateEventType.isCWUpdated(updateEventType)) {
                n4 = n3;
                if (this.cwVideos != null) {
                    final Iterator<CWVideo> iterator2 = this.cwVideos.iterator();
                    while (true) {
                        n4 = n3;
                        if (!iterator2.hasNext()) {
                            break;
                        }
                        if (iterator2.next().getHorzDispUrl() == null) {
                            continue;
                        }
                        ++n3;
                    }
                }
            }
            int n5 = n4;
            if (UpdateEventType.isIQUpdated(updateEventType)) {
                n5 = n4;
                if (this.iqVideos != null) {
                    final Iterator<Video> iterator3 = this.iqVideos.iterator();
                    while (true) {
                        n5 = n4;
                        if (!iterator3.hasNext()) {
                            break;
                        }
                        if (iterator3.next().getHorzDispUrl() == null) {
                            continue;
                        }
                        ++n4;
                    }
                }
            }
            int n6 = n5;
            if (UpdateEventType.isRecoListUpdated(updateEventType)) {
                n6 = n5;
                if (this.recoVideos != null) {
                    final Iterator<Video> iterator4 = this.recoVideos.iterator();
                    while (true) {
                        n6 = n5;
                        if (!iterator4.hasNext()) {
                            break;
                        }
                        if (iterator4.next().getHorzDispUrl() == null) {
                            continue;
                        }
                        ++n5;
                    }
                }
            }
            return n6;
        }
        
        public String toJsonString() {
            return FalcorParseUtils.getGson().toJson(this);
        }
    }
}
