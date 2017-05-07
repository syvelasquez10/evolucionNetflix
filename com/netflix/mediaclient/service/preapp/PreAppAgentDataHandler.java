// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preapp;

import java.util.HashSet;
import com.netflix.mediaclient.util.data.DataRepository$DataSavedCallback;
import android.content.Intent;
import com.netflix.mediaclient.service.pservice.PService;
import com.netflix.mediaclient.service.pservice.PDiskDataRepository$LoadCallback;
import com.netflix.mediaclient.service.pservice.PServiceWidgetAgent;
import com.netflix.mediaclient.service.pservice.PDiskData$ImageType;
import com.netflix.mediaclient.service.resfetcher.LoggingResourceFetcherCallback;
import com.netflix.mediaclient.service.ServiceAgent$BrowseAgentInterface;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.service.pservice.PVideo;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.service.pservice.PDiskData$ListType;
import java.util.Set;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.pservice.PDiskData;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;

public class PreAppAgentDataHandler
{
    public static final int PREAPP_MAX_LISTS = 6;
    public static final int PREAPP_MAX_NON_MEMBER_VIDEOS = 12;
    public static final int PREAPP_MAX_VIDEOS_IN_LIST = 6;
    private static final String TAG = "nf_preappagentdatahandler";
    private static final boolean TO_FETCH_FROM_CACHE_ONLY = false;
    private static Context mContext;
    private static ServiceAgent mServiceAgent;
    
    public PreAppAgentDataHandler(final Context mContext, final ServiceAgent mServiceAgent) {
        PreAppAgentDataHandler.mContext = mContext;
        PreAppAgentDataHandler.mServiceAgent = mServiceAgent;
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
        new BackgroundTask().execute(new PreAppAgentDataHandler$13(this, substring, s));
    }
    
    private void clearOldImages(final PDiskData pDiskData) {
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s : pDiskData.urlMap.keySet()) {
            if (!this.isUrlPresentInAnyList(s, pDiskData)) {
                list.add(s);
            }
        }
        Log.d("nf_preappagentdatahandler", "clearImagesNotPresentInNewList, " + list);
        for (final String s2 : list) {
            this.clearOldImage(pDiskData.urlMap.get(s2));
            pDiskData.urlMap.remove(s2);
        }
    }
    
    private void collectFetchCallbacks(final Set<PDiskData$ListType> set, final PreAppAgentEventType preAppAgentEventType) {
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.BILLBOARD);
        }
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.CW);
        }
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.IQ);
        }
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.STANDARD_FIRST);
        }
        if (PreAppAgentEventType.isSecondStandardListUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.STANDARD_SECOND);
        }
        if (PreAppAgentEventType.isNonMemberListUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.NON_MEMBER);
        }
        if (PreAppAgentEventType.isListInfoUpdated(preAppAgentEventType)) {
            set.add(PDiskData$ListType.LOMO_INFO);
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
    
    private void copyListInfoIntoDiskData(final PDiskData pDiskData, final List<LoMo> list) {
        final Map<String, String> lomoMap = pDiskData.lomoMap;
        lomoMap.clear();
        for (final LoMo loMo : list) {
            if (LoMoType.BILLBOARD.equals(loMo.getType())) {
                lomoMap.put(PDiskData$ListType.BILLBOARD.getValue(), loMo.getTitle());
            }
            else if (LoMoType.CONTINUE_WATCHING.equals(loMo.getType())) {
                lomoMap.put(PDiskData$ListType.CW.getValue(), loMo.getTitle());
            }
            else if (LoMoType.INSTANT_QUEUE.equals(loMo.getType())) {
                lomoMap.put(PDiskData$ListType.IQ.getValue(), loMo.getTitle());
            }
            else if (LoMoType.STANDARD.equals(loMo.getType()) && lomoMap.get(PDiskData$ListType.STANDARD_FIRST.getValue()) == null) {
                lomoMap.put(PDiskData$ListType.STANDARD_FIRST.getValue(), loMo.getTitle());
            }
            else {
                if (!LoMoType.STANDARD.equals(loMo.getType()) || lomoMap.get(PDiskData$ListType.STANDARD_SECOND.getValue()) != null) {
                    continue;
                }
                lomoMap.put(PDiskData$ListType.STANDARD_SECOND.getValue(), loMo.getTitle());
            }
        }
    }
    
    private void copyListIntoDiskData(final PDiskData pDiskData, final List<Video> list, final PDiskData$ListType pDiskData$ListType) {
        List<PVideo> list2;
        if (PDiskData$ListType.IQ.equals(pDiskData$ListType)) {
            list2 = pDiskData.iqList;
        }
        else if (PDiskData$ListType.STANDARD_FIRST.equals(pDiskData$ListType)) {
            list2 = pDiskData.standardFirstList;
        }
        else if (PDiskData$ListType.STANDARD_SECOND.equals(pDiskData$ListType)) {
            list2 = pDiskData.standardSecondList;
        }
        else if (PDiskData$ListType.NON_MEMBER.equals(pDiskData$ListType)) {
            list2 = pDiskData.nonMemberList;
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
    
    private void fetchLists(final PreAppAgentEventType preAppAgentEventType, final SimpleBrowseAgentCallback simpleBrowseAgentCallback, final SimpleBrowseAgentCallback simpleBrowseAgentCallback2, final SimpleBrowseAgentCallback simpleBrowseAgentCallback3, final SimpleBrowseAgentCallback simpleBrowseAgentCallback4, final SimpleBrowseAgentCallback simpleBrowseAgentCallback5, final SimpleBrowseAgentCallback simpleBrowseAgentCallback6) {
        final ServiceAgent$BrowseAgentInterface browseAgent = PreAppAgentDataHandler.mServiceAgent.getBrowseAgent();
        if (PreAppAgentEventType.isListInfoUpdated(preAppAgentEventType)) {
            browseAgent.fetchLoMos(0, 19, simpleBrowseAgentCallback6);
        }
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            browseAgent.fetchBillboards(6, false, simpleBrowseAgentCallback);
        }
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            browseAgent.fetchCW(6, false, simpleBrowseAgentCallback);
        }
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            browseAgent.fetchIQ(6, false, simpleBrowseAgentCallback2);
        }
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            browseAgent.fetchRecommendedList(0, 6, false, simpleBrowseAgentCallback3);
        }
        if (PreAppAgentEventType.isSecondStandardListUpdated(preAppAgentEventType)) {
            browseAgent.fetchRecommendedList(1, 6, false, simpleBrowseAgentCallback4);
        }
        if (PreAppAgentEventType.isNonMemberListUpdated(preAppAgentEventType)) {
            browseAgent.fetchNonMemberVideos(12, true, simpleBrowseAgentCallback5);
        }
    }
    
    private void fetchUrl(final String s, final String s2, final LoggingResourceFetcherCallback loggingResourceFetcherCallback) {
        if (s2 != null) {
            new BackgroundTask().execute(new PreAppAgentDataHandler$9(this, s2, loggingResourceFetcherCallback));
            return;
        }
        Log.d("nf_preappagentdatahandler", String.format("video.id: %s, url is null", s));
    }
    
    private void fetchUrlsOfList(final List<PVideo> list, final Map<String, String> map, final PDiskData$ListType pDiskData$ListType, final LoggingResourceFetcherCallback loggingResourceFetcherCallback) {
        if (list != null) {
            for (final PVideo pVideo : list) {
                if (PDiskData$ListType.CW.equals(pDiskData$ListType)) {
                    final String imageUrl = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TRICKPLAY);
                    if (!map.containsKey(imageUrl)) {
                        this.fetchUrl(pVideo.id, imageUrl, loggingResourceFetcherCallback);
                    }
                    else {
                        Log.d("nf_preappagentdatahandler", String.format("%s(%s), url:%s exists in urlMap - skip", pVideo.id, PDiskData$ImageType.TRICKPLAY, imageUrl));
                    }
                }
                final String imageUrl2 = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.HORIZONTAL_ART);
                if (!map.containsKey(imageUrl2)) {
                    this.fetchUrl(pVideo.id, imageUrl2, loggingResourceFetcherCallback);
                }
                else {
                    Log.d("nf_preappagentdatahandler", String.format("%s(%s), url:%s exists in urlMap - skip", pVideo.id, PDiskData$ImageType.HORIZONTAL_ART, imageUrl2));
                }
                final String imageUrl3 = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TITLE_CARD);
                if (!map.containsKey(imageUrl3) && StringUtils.isNotEmpty(imageUrl3)) {
                    this.fetchUrl(pVideo.id, imageUrl3, loggingResourceFetcherCallback);
                }
                else {
                    Log.d("nf_preappagentdatahandler", String.format("%s(%s), url:%s exists in urlMap - skip", pVideo.id, PDiskData$ImageType.TITLE_CARD, imageUrl3));
                }
            }
        }
    }
    
    private int getListUrlFetchCount(final List<PVideo> list, final Map<String, String> map, final boolean b) {
        if (list == null) {
            return 0;
        }
        final Iterator<PVideo> iterator = list.iterator();
        int n = 0;
    Label_0152_Outer:
        while (iterator.hasNext()) {
            final PVideo pVideo = iterator.next();
            final String imageUrl = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.HORIZONTAL_ART);
            int n2 = n;
            if (imageUrl != null) {
                n2 = n;
                if (!map.containsKey(imageUrl)) {
                    n2 = n + 1;
                }
            }
            final String imageUrl2 = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TITLE_CARD);
            int n3 = n2;
            if (imageUrl2 != null) {
                n3 = n2;
                if (!map.containsKey(imageUrl2)) {
                    n3 = n2 + 1;
                }
            }
            while (true) {
                Label_0162: {
                    if (!b) {
                        break Label_0162;
                    }
                    final String imageUrl3 = PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TRICKPLAY);
                    if (imageUrl3 == null || map.containsKey(imageUrl3)) {
                        break Label_0162;
                    }
                    final int n4 = n3 + 1;
                    n = n4;
                    continue Label_0152_Outer;
                }
                final int n4 = n3;
                continue;
            }
        }
        return n;
    }
    
    private int getUrlFetchCount(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        int n;
        if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
            n = this.getListUrlFetchCount(pDiskData.billboardList, pDiskData.urlMap, false) + 0;
        }
        else {
            n = 0;
        }
        int n2 = n;
        if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
            n2 = n + this.getListUrlFetchCount(pDiskData.cwList, pDiskData.urlMap, true);
        }
        int n3 = n2;
        if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
            n3 = n2 + this.getListUrlFetchCount(pDiskData.iqList, pDiskData.urlMap, false);
        }
        int n4 = n3;
        if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
            n4 = n3 + this.getListUrlFetchCount(pDiskData.standardFirstList, pDiskData.urlMap, false);
        }
        int n5 = n4;
        if (PreAppAgentEventType.isSecondStandardListUpdated(preAppAgentEventType)) {
            n5 = n4 + this.getListUrlFetchCount(pDiskData.standardSecondList, pDiskData.urlMap, false);
        }
        int n6 = n5;
        if (PreAppAgentEventType.isNonMemberListUpdated(preAppAgentEventType)) {
            n6 = n5 + this.getListUrlFetchCount(pDiskData.nonMemberList, pDiskData.urlMap, false);
        }
        return n6;
    }
    
    private boolean isUrlPresentInAnyList(final String s, final PDiskData pDiskData) {
        return this.isUrlPresentInList(s, pDiskData.billboardList) || this.isUrlPresentInList(s, pDiskData.cwList) || this.isUrlPresentInList(s, pDiskData.iqList) || this.isUrlPresentInList(s, pDiskData.standardFirstList) || this.isUrlPresentInList(s, pDiskData.standardSecondList) || this.isUrlPresentInList(s, pDiskData.nonMemberList);
    }
    
    private boolean isUrlPresentInList(final String s, final List<PVideo> list) {
        if (list == null) {
            return false;
        }
        for (final PVideo pVideo : list) {
            if (StringUtils.safeEquals(s, PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.HORIZONTAL_ART)) || StringUtils.safeEquals(s, PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TITLE_CARD)) || StringUtils.safeEquals(s, PServiceWidgetAgent.getImageUrl(pVideo, PDiskData$ImageType.TRICKPLAY))) {
                return true;
            }
        }
        return false;
    }
    
    private void loadFromDisk(final PDiskDataRepository$LoadCallback pDiskDataRepository$LoadCallback) {
        new BackgroundTask().execute(new PreAppAgentDataHandler$12(this, pDiskDataRepository$LoadCallback));
    }
    
    private PDiskData mergeData(PDiskData pDiskData, PDiskData pDiskData2, final PreAppAgentEventType preAppAgentEventType) {
        if (pDiskData2 == null) {
            return pDiskData;
        }
        Log.d("nf_preappagentdatahandler", String.format("mergeData updateType:%s", preAppAgentEventType));
        switch (PreAppAgentDataHandler$15.$SwitchMap$com$netflix$mediaclient$service$preapp$PreAppAgentEventType[preAppAgentEventType.ordinal()]) {
            default: {
                if (Log.isLoggable()) {
                    Log.w("nf_preappagentdatahandler", "called merge data for unexpected update type: " + preAppAgentEventType);
                }
                pDiskData = new PDiskData(pDiskData);
                break;
            }
            case 1: {
                pDiskData = new PDiskData(pDiskData);
                pDiskData.nonMemberList = pDiskData2.nonMemberList;
                pDiskData.urlMap = pDiskData2.urlMap;
                break;
            }
            case 2: {
                pDiskData2 = new PDiskData(pDiskData2);
                pDiskData2.cwList = pDiskData.cwList;
                pDiskData = pDiskData2;
                break;
            }
            case 3: {
                pDiskData2 = new PDiskData(pDiskData2);
                pDiskData2.iqList = pDiskData.iqList;
                pDiskData = pDiskData2;
                break;
            }
            case 4: {
                pDiskData2 = new PDiskData(pDiskData2);
                pDiskData2.nonMemberList = pDiskData.nonMemberList;
                pDiskData = pDiskData2;
                break;
            }
        }
        Log.d("nf_preappagentdatahandler", "merged data:");
        pDiskData.print();
        return pDiskData;
    }
    
    private void notifyOthers(final Context context, final PreAppAgentEventType preAppAgentEventType) {
        Log.d("nf_preappagentdatahandler", "notifyOthers updateType:" + preAppAgentEventType);
        if (!PreAppAgentDataHandler.mServiceAgent.getPreAppAgent().isWidgetInstalled() && !PService.isRemoteUiDevice()) {
            Log.d("nf_preappagentdatahandler", "skipNotiying others - !widgetInstalled & !isRemoteUiDevice & !TestCode");
        }
        else {
            final String s = null;
            String s2 = null;
            switch (PreAppAgentDataHandler$15.$SwitchMap$com$netflix$mediaclient$service$preapp$PreAppAgentEventType[preAppAgentEventType.ordinal()]) {
                default: {
                    Log.d("nf_preappagentdatahandler", "unknown event type received");
                    s2 = s;
                    break;
                }
                case 1: {
                    s2 = "com.netflix.mediaclient.intent.action.ALL_MEMBER_UPDATED_FROM_PREAPP_AGENT";
                    break;
                }
                case 2: {
                    s2 = "com.netflix.mediaclient.intent.action.CW_UPDATED_FROM_PREAPP_AGENT";
                    break;
                }
                case 3: {
                    s2 = "com.netflix.mediaclient.intent.action.IQ_UPDATED_FROM_PREAPP_AGENT";
                    break;
                }
                case 4: {
                    s2 = "com.netflix.mediaclient.intent.action.NON_MEMBER_UPDATED_FROM_PREAPP_AGENT";
                    break;
                }
                case 5: {
                    s2 = "com.netflix.mediaclient.intent.action.ACTION_ACCOUNT_DEACTIVATED_FROM_PREAPP_AGENT";
                    break;
                }
            }
            if (!StringUtils.isEmpty(s2)) {
                final Intent intent = new Intent(s2);
                intent.addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_AGENT");
                intent.setClass(context, (Class)PService.class);
                if (Log.isLoggable()) {
                    Log.d("nf_preappagentdatahandler", String.format("sending intent: %s", intent));
                }
                context.startService(intent);
            }
        }
    }
    
    private void proceedAfterFetchOfLists(final Set<PDiskData$ListType> set, final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        if (!set.isEmpty()) {
            Log.d("nf_preappagentdatahandler", String.format("waiting for %s", set));
            return;
        }
        Log.d("nf_preappagentdatahandler", "lists/videos fetched");
        this.proceedToLoadFromDisk(pDiskData, preAppAgentEventType);
    }
    
    private void proceedToFetchOfImages(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        final int urlFetchCount = this.getUrlFetchCount(pDiskData, preAppAgentEventType);
        final PreAppAgentDataHandler$8 preAppAgentDataHandler$8 = new PreAppAgentDataHandler$8(this, urlFetchCount, pDiskData, preAppAgentEventType);
        Log.d("nf_preappagentdatahandler", String.format("urlFetchCount=%d", urlFetchCount));
        if (urlFetchCount <= 0) {
            Log.d("nf_preappagentdatahandler", "no images to fetch - store newData");
            this.proceedToStoreAndNotify(pDiskData, preAppAgentEventType);
        }
        else {
            if (PreAppAgentEventType.isBBUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.billboardList, pDiskData.urlMap, PDiskData$ListType.BILLBOARD, preAppAgentDataHandler$8);
            }
            if (PreAppAgentEventType.isCWUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.cwList, pDiskData.urlMap, PDiskData$ListType.CW, preAppAgentDataHandler$8);
            }
            if (PreAppAgentEventType.isIQUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.iqList, pDiskData.urlMap, PDiskData$ListType.IQ, preAppAgentDataHandler$8);
            }
            if (PreAppAgentEventType.isFirstStandardListUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.standardFirstList, pDiskData.urlMap, PDiskData$ListType.STANDARD_FIRST, preAppAgentDataHandler$8);
            }
            if (PreAppAgentEventType.isSecondStandardListUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.standardSecondList, pDiskData.urlMap, PDiskData$ListType.STANDARD_SECOND, preAppAgentDataHandler$8);
            }
            if (PreAppAgentEventType.isNonMemberListUpdated(preAppAgentEventType)) {
                this.fetchUrlsOfList(pDiskData.nonMemberList, pDiskData.urlMap, PDiskData$ListType.NON_MEMBER, preAppAgentDataHandler$8);
            }
        }
    }
    
    private void proceedToLoadFromDisk(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        this.loadFromDisk(new PreAppAgentDataHandler$7(this, pDiskData, preAppAgentEventType));
    }
    
    private void proceedToStoreAndNotify(final PDiskData pDiskData, final PreAppAgentEventType preAppAgentEventType) {
        if (pDiskData == null) {
            Log.e("nf_preappagentdatahandler", "newData is null");
            return;
        }
        final PreAppAgentDataHandler$10 preAppAgentDataHandler$10 = new PreAppAgentDataHandler$10(this, preAppAgentEventType);
        pDiskData.print();
        new BackgroundTask().execute(new PreAppAgentDataHandler$11(this, pDiskData, preAppAgentDataHandler$10));
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
    
    public void clear(final PreAppAgentEventType preAppAgentEventType) {
        if (!PreAppAgentEventType.shouldClearData(preAppAgentEventType)) {
            Log.w("nf_preappagentdatahandler", String.format("skip clearing data - invalid updateType= %s", preAppAgentEventType));
            return;
        }
        this.loadFromDisk(new PreAppAgentDataHandler$14(this, preAppAgentEventType));
    }
    
    public void update(final PreAppAgentEventType preAppAgentEventType) {
        final PDiskData experienceType = new PDiskData();
        final HashSet<PDiskData$ListType> set = new HashSet<PDiskData$ListType>();
        this.setExperienceType(experienceType);
        this.collectFetchCallbacks(set, preAppAgentEventType);
        this.fetchLists(preAppAgentEventType, new PreAppAgentDataHandler$1(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$2(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$3(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$4(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$5(this, experienceType, set, preAppAgentEventType), new PreAppAgentDataHandler$6(this, experienceType, set, preAppAgentEventType));
    }
}
