// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import android.content.Intent;
import com.netflix.mediaclient.service.pservice.PService;
import java.util.Map;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;
import com.netflix.mediaclient.service.resfetcher.ResourceFetcherCallback;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import java.util.Iterator;
import com.netflix.mediaclient.service.pservice.PVideo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListName;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;

public class PreAppAgentDataHandler
{
    public static final int PREAPP_MAX_LISTS = 5;
    public static final int PREAPP_MAX_VIDEOS_IN_LIST = 5;
    private static final String TAG = "nf_preappagentdatahandler";
    private static Context mContext;
    private static ServiceAgent mServiceAgent;
    
    public PreAppAgentDataHandler(final Context mContext, final ServiceAgent mServiceAgent) {
        PreAppAgentDataHandler.mContext = mContext;
        PreAppAgentDataHandler.mServiceAgent = mServiceAgent;
    }
    
    private void addListsToFetch(final Set<PDiskData$ListName> set, final PreAppAgentEventType preAppAgentEventType) {
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListName.BILLBOARD);
        }
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListName.CW);
        }
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListName.IQ);
        }
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListName.STANDARD_FIRST);
        }
    }
    
    private void clearOldImage(final String s) {
        if (StringUtils.isEmpty(s)) {
            return;
        }
        final int lastIndex = s.lastIndexOf("/");
        String substring;
        if (lastIndex > 0 && lastIndex < s.length() - 1) {
            substring = s.substring(lastIndex + 1);
        }
        else {
            substring = s;
        }
        if (!PreAppAgentDataHandler.mServiceAgent.getResourceFetcher().deleteLocalResource(substring)) {
            Log.w("nf_preappagentdatahandler", String.format("localFilename: %s, filename: %s delete failed", s, substring));
            return;
        }
        Log.d("nf_preappagentdatahandler", String.format("deleted local file: %s", s));
    }
    
    private void clearOldImages(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        if (pDiskData != null) {
            switch (PreAppAgentDataHandler$9.$SwitchMap$com$netflix$mediaclient$service$preapp$PreAppAgentEventType[preAppAgentEventType.ordinal()]) {
                default: {}
                case 1: {
                    final Iterator<String> iterator = pDiskData.urlMap.values().iterator();
                    while (iterator.hasNext()) {
                        this.clearOldImage(iterator.next());
                    }
                    break;
                }
                case 2: {
                    final Iterator<PVideo> iterator2 = pDiskData.cwList.iterator();
                    while (iterator2.hasNext()) {
                        this.clearOldImage(pDiskData.urlMap.get(iterator2.next().horzDispUrl));
                    }
                    break;
                }
                case 3: {
                    final Iterator<PVideo> iterator3 = pDiskData.iqList.iterator();
                    while (iterator3.hasNext()) {
                        this.clearOldImage(pDiskData.urlMap.get(iterator3.next().horzDispUrl));
                    }
                    break;
                }
            }
        }
    }
    
    private void copyBBListIntoDiskData(final PDiskData pDiskData, final List<Billboard> list) {
        if (list != null && pDiskData != null) {
            final Iterator<Billboard> iterator = list.iterator();
            while (iterator.hasNext()) {
                pDiskData.billboardList.add(new PVideo(iterator.next()));
            }
        }
    }
    
    private void copyCWListIntoDiskData(final PDiskData pDiskData, final List<CWVideo> list) {
        if (list != null && pDiskData != null) {
            final Iterator<CWVideo> iterator = list.iterator();
            while (iterator.hasNext()) {
                pDiskData.cwList.add(new PVideo(iterator.next()));
            }
        }
    }
    
    private void copyListIntoDiskData(final PDiskData pDiskData, final List<Video> list, final PDiskData$ListName pDiskData$ListName) {
        List<PVideo> list2;
        if (PDiskData$ListName.IQ.equals(pDiskData$ListName)) {
            list2 = pDiskData.iqList;
        }
        else if (PDiskData$ListName.STANDARD_FIRST.equals(pDiskData$ListName)) {
            list2 = pDiskData.standardFirstList;
        }
        else {
            list2 = null;
        }
        if (list2 != null && pDiskData != null && list != null) {
            final Iterator<Video> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(new PVideo(iterator.next()));
            }
        }
    }
    
    private void fetchLists(final PreAppAgentEventType preAppAgentEventType, final SimpleBrowseAgentCallback simpleBrowseAgentCallback, final SimpleBrowseAgentCallback simpleBrowseAgentCallback2, final SimpleBrowseAgentCallback simpleBrowseAgentCallback3) {
        final ServiceAgent$BrowseAgentInterface browseAgent = PreAppAgentDataHandler.mServiceAgent.getBrowseAgent();
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            browseAgent.fetchBillboardsFromCache(5, simpleBrowseAgentCallback);
        }
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            browseAgent.fetchCWFromCache(5, simpleBrowseAgentCallback);
        }
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            browseAgent.fetchIQFromCache(5, simpleBrowseAgentCallback2);
        }
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            browseAgent.fetchRecommendedListFromCache(5, simpleBrowseAgentCallback3);
        }
    }
    
    private void fetchUrl(final String s, final String s2, final LoggingResourceFetcherCallback loggingResourceFetcherCallback) {
        if (s2 != null) {
            PreAppAgentDataHandler.mServiceAgent.getResourceFetcher().fetchResource(s2, IClientLogging$AssetType.boxArt, loggingResourceFetcherCallback);
            return;
        }
        Log.d("nf_preappagentdatahandler", String.format("video.id: %s, getHorzDispUrl() is null", s));
    }
    
    private void fetchUrlsOfList(final List<PVideo> list, final LoggingResourceFetcherCallback loggingResourceFetcherCallback) {
        if (list != null) {
            for (final PVideo pVideo : list) {
                this.fetchUrl(pVideo.id, pVideo.horzDispUrl, loggingResourceFetcherCallback);
            }
        }
    }
    
    private int getListUrlFetchCount(final List<PVideo> list) {
        if (list == null) {
            return 0;
        }
        final Iterator<PVideo> iterator = list.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            if (iterator.next().horzDispUrl != null) {
                ++n;
            }
        }
        return n;
    }
    
    private int getUrlFetchCount(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        int n = 0;
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            n = 0 + this.getListUrlFetchCount(pDiskData.billboardList);
        }
        int n2 = n;
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            n2 = n + this.getListUrlFetchCount(pDiskData.cwList);
        }
        int n3 = n2;
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            n3 = n2 + this.getListUrlFetchCount(pDiskData.iqList);
        }
        int n4 = n3;
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            n4 = n3 + this.getListUrlFetchCount(pDiskData.standardFirstList);
        }
        return n4;
    }
    
    private void loadFromDisk(final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback) {
        new BackgroundTask().execute(new PreAppAgentDataHandler$8(this, pDiskDataRepository$LoadCallback));
    }
    
    private PDiskData mergeData(PDiskData pDiskData, final PDiskData pDiskData2, final PreAppAgentEventType preAppAgentEventType) {
        if (pDiskData2 == null) {
            return pDiskData;
        }
        switch (PreAppAgentDataHandler$9.$SwitchMap$com$netflix$mediaclient$service$preapp$PreAppAgentEventType[preAppAgentEventType.ordinal()]) {
            default: {
                if (Log.isLoggable("nf_preappagentdatahandler", 3)) {
                    Log.w("nf_preappagentdatahandler", "called merge data for unexpected update type: " + preAppAgentEventType);
                }
                pDiskData = new PDiskData(pDiskData);
                break;
            }
            case 1: {
                pDiskData = new PDiskData(pDiskData);
                break;
            }
            case 2: {
                final PDiskData pDiskData3 = new PDiskData(pDiskData2);
                pDiskData3.cwList = pDiskData.cwList;
                final Iterator<PVideo> iterator = pDiskData2.cwList.iterator();
                while (iterator.hasNext()) {
                    pDiskData3.urlMap.remove(iterator.next().horzDispUrl);
                }
                pDiskData3.urlMap.putAll(pDiskData.urlMap);
                pDiskData = pDiskData3;
                break;
            }
            case 3: {
                final PDiskData pDiskData4 = new PDiskData(pDiskData2);
                pDiskData4.iqList = pDiskData.iqList;
                final Iterator<PVideo> iterator2 = pDiskData2.iqList.iterator();
                while (iterator2.hasNext()) {
                    pDiskData4.urlMap.remove(iterator2.next().horzDispUrl);
                }
                pDiskData4.urlMap.putAll(pDiskData.urlMap);
                pDiskData = pDiskData4;
                break;
            }
        }
        return pDiskData;
    }
    
    private void notifyOthers(final Context context, final PreAppAgentEventType preAppAgentEventType) {
        Log.d("nf_preappagentdatahandler", "notifyOthers updateType:" + preAppAgentEventType);
        if (!PreAppAgentDataHandler.mServiceAgent.getPreAppAgent().isWidgetInstalled() && !PService.isRemoteUiDevice() && !PService.WIDGET_ENABLED_FOR_TEST) {
            Log.d("nf_preappagentdatahandler", "skipNotiying others - !widgetInstalled & !isRemoteUiDevice & !TestCode");
        }
        else {
            final String s = null;
            String s2 = null;
            switch (PreAppAgentDataHandler$9.$SwitchMap$com$netflix$mediaclient$service$preapp$PreAppAgentEventType[preAppAgentEventType.ordinal()]) {
                default: {
                    Log.d("nf_preappagentdatahandler", "unknown event type received");
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
                case 4: {
                    s2 = "com.netflix.mediaclient.intent.action.PREAPP_AGENT_FROM_ACTIVE_DISK_WRITE";
                    break;
                }
            }
            if (!StringUtils.isEmpty(s2)) {
                final Intent intent = new Intent(s2);
                intent.addCategory("com.netflix.mediaclient.intent.category.PREAPP_AGENT_FROM_CATEGORY");
                intent.setClass(context, (Class)PService.class);
                if (Log.isLoggable("nf_preappagentdatahandler", 3)) {
                    Log.d("nf_preappagentdatahandler", String.format("sending intent: %s", intent));
                }
                context.startService(intent);
            }
        }
    }
    
    private void proceedAfterFetchOfImages(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        final int urlFetchCount = this.getUrlFetchCount(pDiskData, preAppAgentEventType);
        final PreAppAgentDataHandler$5 preAppAgentDataHandler$5 = new PreAppAgentDataHandler$5(this, urlFetchCount, pDiskData, preAppAgentEventType);
        if (urlFetchCount <= 0) {
            this.storeAndNotify(pDiskData, preAppAgentEventType);
        }
        else {
            if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.billboardList, preAppAgentDataHandler$5);
            }
            if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.cwList, preAppAgentDataHandler$5);
            }
            if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.iqList, preAppAgentDataHandler$5);
            }
            if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.standardFirstList, preAppAgentDataHandler$5);
            }
        }
    }
    
    private void proceedAfterFetchOfLists(final Set<PDiskData$ListName> set, final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        if (!set.isEmpty()) {
            Log.d("nf_preappagentdatahandler", String.format("waiting for %s", set));
            return;
        }
        this.proceedAfterLoadFromDisk(pDiskData, preAppAgentEventType);
    }
    
    private void proceedAfterLoadFromDisk(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        this.loadFromDisk(new PreAppAgentDataHandler$4(this, preAppAgentEventType, pDiskData));
    }
    
    private void setExperienceType(final PDiskData pDiskData) {
        String preAppPartnerExperience;
        if (StringUtils.isEmpty(preAppPartnerExperience = PreAppAgentDataHandler.mServiceAgent.getConfigurationAgent().getPreAppPartnerExperience())) {
            preAppPartnerExperience = "default";
        }
        pDiskData.preAppPartnerExperience = preAppPartnerExperience;
        final String preAppWidgetExperience = PreAppAgentDataHandler.mServiceAgent.getConfigurationAgent().getPreAppWidgetExperience();
        if (StringUtils.isEmpty(preAppWidgetExperience)) {}
        pDiskData.preAppWidgetExperience = preAppWidgetExperience;
    }
    
    private void storeAndNotify(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        new BackgroundTask().execute(new PreAppAgentDataHandler$7(this, pDiskData, new PreAppAgentDataHandler$6(this, preAppAgentEventType)));
    }
    
    public void update(final PreAppAgentEventType preAppAgentEventType) {
        final PDiskData experienceType = new PDiskData();
        final HashSet<PDiskData$ListName> set = new HashSet<PDiskData$ListName>();
        this.setExperienceType(experienceType);
        this.addListsToFetch(set, preAppAgentEventType);
        this.fetchLists(preAppAgentEventType, new PreAppAgentDataHandler$1(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$2(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$3(this, experienceType, set, preAppAgentEventType));
    }
}
