// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.model.leafs.Video$InQueue;
import com.netflix.falkor.task.UpdateExpiryAdvisoryStatusTask;
import com.netflix.model.leafs.advisory.ExpiringContentAdvisory$ContentAction;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.model.leafs.Video$Bookmark;
import com.netflix.falkor.task.SetVideoRatingTask;
import com.google.gson.ExclusionStrategy;
import com.google.gson.GsonBuilder;
import java.io.Writer;
import com.netflix.falkor.task.FetchSearchResultsTask;
import com.netflix.falkor.task.RemoveFromQueueTask;
import com.netflix.falkor.task.RefreshPopularTitlesTask;
import com.netflix.falkor.task.RefreshIqTask;
import com.netflix.falkor.task.RefreshDiscoveryTask;
import com.netflix.falkor.task.RefreshCwTask;
import com.netflix.falkor.task.PrefetchVideoListDetailsTask;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import com.netflix.falkor.task.PrefetchLoLoMoTask;
import com.netflix.falkor.task.PrefetchGenreLoLoMoTask;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.falkor.task.MarkNotificationAsReadTask;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import com.netflix.falkor.task.LogPostPlayImpression;
import com.netflix.falkor.task.LogBillboardActivityTask;
import com.netflix.mediaclient.servicemgr.BillboardInteractionType;
import com.netflix.model.branches.MementoVideoSwatch;
import java.util.LinkedHashSet;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.text.TextUtils;
import com.netflix.model.branches.FalkorObject;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.falkor.task.FetchDiscoveryVideosTask;
import com.netflix.falkor.task.FetchVideoSummaryTask;
import com.netflix.falkor.task.CmpTaskWrapper;
import com.netflix.falkor.task.CmpTaskDetails;
import com.netflix.falkor.task.FetchSimilarVideosTask;
import com.netflix.mediaclient.service.falkor.Falkor$SimilarRequestType;
import com.netflix.falkor.task.FetchShowDetailsTask;
import com.netflix.falkor.task.FetchSeasonsTask;
import com.netflix.falkor.task.FetchSeasonDetailsTask;
import com.netflix.falkor.task.FetchScenePositionTask;
import com.netflix.falkor.task.FetchPostPlayVideosTask;
import com.netflix.mediaclient.ui.player.PostPlayRequestContext;
import com.netflix.falkor.task.FetchPersonRelated;
import com.netflix.falkor.task.FetchPersonDetail;
import com.netflix.falkor.task.FetchOfflineGeoPlayabilityTask;
import com.netflix.falkor.task.FetchNotificationsTask;
import com.netflix.falkor.task.FetchNonMemberVideosTask;
import com.netflix.falkor.task.FetchMovieDetailsTask;
import com.netflix.falkor.task.FetchLoMosTask;
import com.netflix.falkor.task.FetchKidsCharacterDetailsTask;
import com.netflix.falkor.task.FetchInteractiveVideoMomentsTask;
import com.netflix.falkor.task.FetchVideosTask;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.falkor.task.FetchGenresTask;
import com.netflix.falkor.task.FetchGenreLoLoMoSummaryTask;
import com.netflix.falkor.task.FetchGenreListTask;
import com.netflix.falkor.task.FetchFalkorVideoTask;
import com.netflix.falkor.task.FetchEpisodesTask;
import com.netflix.falkor.task.FetchEpisodeDetailsTask;
import com.netflix.falkor.task.FetchCwVideosTask;
import com.netflix.falkor.task.CmpTaskMode;
import com.netflix.falkor.task.FetchBillboardVideosTask;
import com.netflix.falkor.task.FetchAdvisoriesTask;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.model.leafs.advisory.Advisory;
import com.netflix.falkor.task.FetchActorDetailsAndRelatedForTitle;
import com.netflix.falkor.task.EndBrowsePlaySessionTask;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import com.netflix.mediaclient.util.StringUtils;
import com.fasterxml.jackson.core.JsonFactory;
import java.io.Reader;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import com.netflix.falkor.task.AddToQueueTask;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.Date;
import java.lang.reflect.Type;
import com.google.gson.stream.JsonWriter;
import com.google.gson.Gson;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.NetflixService;
import android.util.Pair;
import com.netflix.mediaclient.servicemgr.interface_.LoMo;
import com.netflix.mediaclient.util.DataUtil$StringPair;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.falkor.Falkor;
import java.util.Iterator;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import com.netflix.mediaclient.util.AlphanumComparator;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Looper;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClient;
import android.os.Handler;
import com.google.gson.JsonParser;
import android.annotation.SuppressLint;

@SuppressLint({ "DefaultLocale" })
public class CachedModelProxy<T extends BranchNode> implements ModelProxy<T>
{
    public static final String ACTION_NOTIFY_OF_RATINGS_CHANGE = "com.netflix.falkor.ACTION_NOTIFY_OF_RATINGS_CHANGE";
    public static final String EXTRA_USER_RATING = "extra_user_rating";
    public static final String EXTRA_USER_THUMB_RATING = "extra_user_thumb_rating";
    public static final String EXTRA_VIDEO_ID = "extra_video_id";
    public static final int FETCH_EPISODES_REQUEST_BATCH_SIZE = 40;
    public static boolean FORCE_CMP_TO_LOCAL_CACHE = false;
    public static final int MAX_SEARCH_RESULTS_PER_SECTION_INDEX = 19;
    private static final String NEWLINE = "\n";
    private static final boolean ORIGINALS_BILLBOARDS_ENABLED = true;
    private static final int PREFETCH_BILLBOARD_VIDEO_INDEX = 9;
    private static final String TAG = "CachedModelProxy";
    private static final String TAG_TIMING = "CachedModelProxy_Timing";
    private static long sLastWriteTimeMS;
    private final JsonParser jsonParser;
    private int lastPrefetchFromVideo;
    private int lastPrefetchToVideo;
    private final Handler mainHandler;
    private final T root;
    private final ServiceProvider serviceProvider;
    private final FalkorVolleyWebClient webClient;
    
    static {
        CachedModelProxy.FORCE_CMP_TO_LOCAL_CACHE = false;
    }
    
    public CachedModelProxy(final ServiceProvider serviceProvider, final T root, final FalkorVolleyWebClient webClient) {
        this.jsonParser = new JsonParser();
        this.serviceProvider = serviceProvider;
        this.root = root;
        this.webClient = webClient;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
    
    private void doDumpCacheToDiskRecursive(final StringBuilder sb, final BranchNode branchNode, final int n, final boolean b) {
        final StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            String s;
            if (i == n - 1) {
                s = " |-";
            }
            else {
                s = " | ";
            }
            sb2.append(s);
        }
        final String string = sb2.toString();
        final ArrayList<Object> list = new ArrayList<Object>(branchNode.getKeys());
        if (b) {
            Collections.sort(list, (Comparator<? super Object>)new AlphanumComparator());
        }
        for (final String s2 : list) {
            final Object value = branchNode.get(s2);
            if (value instanceof Ref) {
                sb.append(string).append(s2).append(" -> ").append(((Ref)value).getRefPath()).append("\n");
            }
            else if (value instanceof Sentinel) {
                sb.append(string).append(s2).append(" -> [sentinel]").append("\n");
            }
            else {
                sb.append(string).append(s2).append("\n");
            }
            if (value instanceof BranchNode) {
                this.doDumpCacheToDiskRecursive(sb, (BranchNode)value, n + 1, true);
            }
        }
    }
    
    private void get(final PQL pql, Object o, final int n, final CachedModelProxy$GetResult cachedModelProxy$GetResult) {
        while (true) {
            int i = 0;
            final PQL pql2;
            Label_0123: {
                Label_0108: {
                    synchronized (this) {
                        if (Falkor.ENABLE_VERBOSE_LOGGING) {
                            Log.v("CachedModelProxy", "get from path: " + pql + ", offset: " + n);
                        }
                        i = pql.getKeySegments().size();
                        if (n < i && o == null) {
                            cachedModelProxy$GetResult.missingPqls.add(pql);
                        }
                        else {
                            if (n != i) {
                                break Label_0123;
                            }
                            if (o == null) {
                                break Label_0108;
                            }
                            cachedModelProxy$GetResult.foundPqls.add(pql);
                        }
                        return;
                    }
                }
                cachedModelProxy$GetResult.missingPqls.add(pql2);
                return;
            }
            if (n > i) {
                throw new IllegalStateException("Offset is invalid");
            }
            if (o instanceof Ref) {
                o = o;
                final Object hardValue = ((Ref)o).getHardValue();
                if (hardValue != null) {
                    this.get(((Ref)o).getRefPath().append(pql2.slice(n)), hardValue, ((Ref)o).getRefPath().getKeySegments().size(), cachedModelProxy$GetResult);
                    return;
                }
                if (((Ref)o).getRefPath() != null) {
                    this.get(((Ref)o).getRefPath().append(pql2.slice(n)), this.root, 0, cachedModelProxy$GetResult);
                    return;
                }
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", "Ref path is null: " + ((Ref)o).getPath());
                }
            }
            else {
                if (o instanceof Exception || o instanceof Undefined) {
                    cachedModelProxy$GetResult.ignoredPqls.add(pql2);
                    return;
                }
                final BranchNode branchNode = (BranchNode)o;
                final Map<K, Integer> value = pql2.getKeySegments().get(n);
                if (value instanceof List) {
                    for (final Map<K, Integer> next : (List<Object>)value) {
                        if (next instanceof Map) {
                            final Map<K, Integer> map = next;
                            final Integer n2 = map.get("from");
                            final Integer n3 = map.get("to");
                            Integer value2;
                            if ((value2 = n2) == null) {
                                value2 = 0;
                            }
                            if (n3 == null) {
                                throw new IllegalStateException("No 'to' param");
                            }
                            for (int i = value2; i <= n3; ++i) {
                                this.get(pql2.replaceKeySegment(n, String.valueOf(i)), o, n, cachedModelProxy$GetResult);
                            }
                        }
                        else {
                            this.get(pql2, branchNode.get(next.toString()), n + 1, cachedModelProxy$GetResult);
                        }
                    }
                    return;
                }
                if (!(value instanceof Map)) {
                    this.get(pql2, branchNode.get(value.toString()), n + 1, cachedModelProxy$GetResult);
                    return;
                }
                final Map<K, Integer> map2 = value;
                final Integer n4 = map2.get("from");
                final Integer n5 = map2.get("to");
                Integer value3;
                if ((value3 = n4) == null) {
                    value3 = 0;
                }
                if (n5 == null) {
                    throw new IllegalStateException("No 'to' param");
                }
                for (int i = value3; i <= n5; ++i) {
                    this.get(pql2.replaceKeySegment(n, String.valueOf(i)), o, n, cachedModelProxy$GetResult);
                }
            }
        }
    }
    
    private DataUtil$StringPair getCurrLomoInfo(final LoMoType loMoType) {
        final Pair<LoMo, String> currLomoByType = this.getCurrLomoByType(loMoType);
        String id;
        if (currLomoByType == null) {
            id = null;
        }
        else {
            id = ((LoMo)currLomoByType.first).getId();
        }
        String value;
        if (currLomoByType == null) {
            value = String.valueOf(-1);
        }
        else {
            value = (String)currLomoByType.second;
        }
        if (Log.isLoggable()) {
            Log.v("CachedModelProxy", "Got curr lomo ID: " + id + ", list index: " + value + ", lomo type: " + loMoType);
        }
        return new DataUtil$StringPair(id, value);
    }
    
    public static long getLastWriteTimeToCacheMS() {
        return CachedModelProxy.sLastWriteTimeMS;
    }
    
    private NetflixService getService() {
        return this.serviceProvider.getService();
    }
    
    private void launchTask(final Runnable runnable) {
        Log.v("CachedModelProxy", "Launching task: %s", runnable.getClass().getSimpleName());
        new BackgroundTask().execute(runnable);
    }
    
    private String rootStringRepresentation() {
        final StringBuilder sb = new StringBuilder();
        sb.append("==START OF CACHE==").append("\n");
        if (Log.isLoggable()) {
            this.doDumpCacheToDiskRecursive(sb, this.root, 0, false);
        }
        sb.append("==END OF CACHE==").append("\n");
        return sb.toString();
    }
    
    private void sendDetailPageReloadBroadcast(final Context context) {
        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.DETAIL_PAGE_REFRESH"));
        Log.v("CachedModelProxy", "Intent DETAIL_PAGE_REFRESH sent");
    }
    
    public static void setLastWriteTimeMS(final long sLastWriteTimeMS) {
        CachedModelProxy.sLastWriteTimeMS = sLastWriteTimeMS;
    }
    
    private void write(final Gson gson, final JsonWriter jsonWriter) {
        write(gson, this.root, jsonWriter);
    }
    
    private static void write(final Gson gson, Object value, final JsonWriter jsonWriter) {
        if (value instanceof BranchNode) {
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "{");
            }
            final BranchNode branchNode = (BranchNode)value;
            jsonWriter.beginObject();
            for (final String s : branchNode.getKeys()) {
                final Object value2 = branchNode.get(s);
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", s + ":");
                }
                if (BranchNodeUtils.shouldIgnoreKey(s)) {
                    if (!Falkor.ENABLE_VERBOSE_LOGGING) {
                        continue;
                    }
                    Log.v("CachedModelProxy", "ignoring key - " + s);
                }
                else {
                    jsonWriter.name(s);
                    write(gson, value2, jsonWriter);
                }
            }
            jsonWriter.endObject();
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "}");
            }
        }
        else if (value instanceof Ref) {
            final PQL refPath = ((Ref)value).getRefPath();
            if (refPath == null) {
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", "null");
                }
                jsonWriter.nullValue();
                return;
            }
            jsonWriter.beginArray();
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "[");
            }
            final Iterator<Object> iterator2 = refPath.getKeySegments().iterator();
            while (iterator2.hasNext()) {
                write(gson, iterator2.next(), jsonWriter);
            }
            jsonWriter.endArray();
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "]");
            }
        }
        else {
            if (value instanceof Sentinel) {
                final Sentinel sentinel = (Sentinel)value;
                jsonWriter.beginObject();
                jsonWriter.name("_sentinel");
                jsonWriter.value(true);
                final Date expires = sentinel.getExpires();
                if (expires != null) {
                    jsonWriter.name("$expires");
                    jsonWriter.value(expires.getTime());
                }
                value = sentinel.getValue();
                if (value != null) {
                    jsonWriter.name("value");
                    gson.toJson(value, value.getClass(), jsonWriter);
                }
                jsonWriter.endObject();
                return;
            }
            if (value != null) {
                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                    Log.v("CachedModelProxy", value.getClass().toString());
                }
                gson.toJson(value, value.getClass(), jsonWriter);
            }
        }
    }
    
    public void addToQueue(final String s, final VideoType videoType, final int n, final String s2, final BrowseAgentCallback browseAgentCallback) {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.INSTANT_QUEUE);
        this.launchTask(new AddToQueueTask(this, s, videoType, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second, n, s2, browseAgentCallback));
    }
    
    public BrowseAgentCallback createHandlerWrapper(final BrowseAgentCallback browseAgentCallback) {
        return new PostToHandlerCallbackWrapper(this.mainHandler, browseAgentCallback);
    }
    
    public void deserializeStream(final Reader reader) {
        final com.fasterxml.jackson.core.JsonParser parser = new JsonFactory().createParser(reader);
        if (this.root instanceof BranchNode) {
            BranchNodeUtils.merge(this.root, parser);
        }
        if (Log.isLoggable()) {
            Log.d("CachedModelProxy", "deserializeStream: completed");
        }
    }
    
    public boolean doesCwExist() {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.CONTINUE_WATCHING);
        if (StringUtils.isEmpty(currLolomoId)) {
            Log.d("CachedModelProxy", "CW doesn't exist - lolomoId is empty");
            return false;
        }
        if (StringUtils.isEmpty((String)currLomoInfo.first)) {
            Log.d("CachedModelProxy", "CW doesn't exist - lomo id is empty");
            return false;
        }
        if (String.valueOf(-1).equals(currLomoInfo.second)) {
            Log.d("CachedModelProxy", "CW doesn't exist - lomo index is invalid");
            return false;
        }
        return true;
    }
    
    public boolean doesDiscoveryRowExist() {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.DISCOVERY_ROW);
        if (StringUtils.isEmpty(currLolomoId)) {
            Log.d("CachedModelProxy", "Discovery doesn't exist - lolomoId is empty");
            return false;
        }
        if (StringUtils.isEmpty((String)currLomoInfo.first)) {
            Log.d("CachedModelProxy", "Discovery doesn't exist - lomo id is empty");
            return false;
        }
        if (String.valueOf(-1).equals(currLomoInfo.second)) {
            Log.d("CachedModelProxy", "Discovery doesn't exist - lomo index is invalid");
            return false;
        }
        return true;
    }
    
    public void dumpCacheToDisk(final File file) {
        if (file != null) {
            FileUtils.writeStringToFile("CachedModelProxy", this.rootStringRepresentation(), file);
            return;
        }
        this.dumpCacheToDisk("cache.txt");
    }
    
    public void dumpCacheToDisk(final String s) {
        FileUtils.writeStringToExternalStorageDirectory("CachedModelProxy", this.rootStringRepresentation(), s);
    }
    
    public void endBrowsePlaySession(final long n, final int n2, final int n3, final int n4, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new EndBrowsePlaySessionTask(this, n, n2, n3, n4, browseAgentCallback));
    }
    
    public void fetchActorDetailsAndRelatedForTitle(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchActorDetailsAndRelatedForTitle(this, s, browseAgentCallback));
    }
    
    public void fetchAdvisories(final String s, final BrowseAgentCallback browseAgentCallback) {
        if (StringUtils.isEmpty(s)) {
            if (browseAgentCallback != null) {
                browseAgentCallback.onAdvisoriesFetched(new ArrayList<Advisory>(0), CommonStatus.INT_ERR_ADVIS_VIDEO_ID_NULL);
            }
            return;
        }
        this.launchTask(new FetchAdvisoriesTask(this, s, browseAgentCallback));
    }
    
    public void fetchBBVideos(final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchBillboardVideosTask(this, n, n2, b, browseAgentCallback));
    }
    
    public void fetchCWVideos(final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        CmpTaskMode cmpTaskMode;
        if (b) {
            cmpTaskMode = CmpTaskMode.FROM_CACHE_ONLY;
        }
        else {
            cmpTaskMode = CmpTaskMode.FROM_CACHE_OR_NETWORK;
        }
        this.launchTask(new FetchCwVideosTask(this, n, n2, cmpTaskMode, browseAgentCallback));
    }
    
    public void fetchCWVideosFromNetwork(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchCwVideosTask(this, n, n2, CmpTaskMode.FROM_NETWORK, browseAgentCallback));
    }
    
    public void fetchEpisodeDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchEpisodeDetailsTask(this, s, s2, browseAgentCallback));
    }
    
    public void fetchEpisodes(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchEpisodesTask(this, s, videoType, n, n2, browseAgentCallback));
    }
    
    public void fetchFalkorVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchFalkorVideoTask(this, s, browseAgentCallback));
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenreListTask(this, browseAgentCallback));
    }
    
    public void fetchGenreLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenreLoLoMoSummaryTask(this, s, browseAgentCallback));
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenresTask(this, s, n, n2, browseAgentCallback));
    }
    
    public void fetchIQVideos(final int n, final int n2, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        final Pair<LoMo, String> currLomoByType = this.getCurrLomoByType(LoMoType.INSTANT_QUEUE);
        if (currLomoByType == null || currLomoByType.first == null) {
            Log.d("CachedModelProxy", "Asked to fetch IQ videos but no IQ lomo currently exists in cache!");
            browseAgentCallback.onVideosFetched(null, CommonStatus.NOT_VALID);
            return;
        }
        this.launchTask(new FetchVideosTask(this, (LoMo)currLomoByType.first, n, n2, b, b2, false, browseAgentCallback));
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.INSTANT_QUEUE);
        if (currLomoInfo == null) {
            Log.w("CachedModelProxy", "Asked to fetch IQ videos but no IQ lomo currently exists in cache!");
            browseAgentCallback.onVideosFetched(null, CommonStatus.NOT_VALID);
            return;
        }
        if (StringUtils.safeEquals((String)currLomoInfo.first, loMo.getId())) {
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "Requested IQ lomo ID and cached IQ lomo ID still match");
            }
        }
        else {
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Requested IQ videos for lomo id: " + loMo.getId() + " but cache IQ lomo id is: " + (String)currLomoInfo.first);
                Log.d("CachedModelProxy", "Updating existing lomo IQ id to cache id value");
            }
            loMo.setId((String)currLomoInfo.first);
        }
        this.launchTask(new FetchVideosTask(this, loMo, n, n2, b, b2, false, browseAgentCallback));
    }
    
    public void fetchInteractiveVideoMoments(final VideoType videoType, final String s, final String s2, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchInteractiveVideoMomentsTask(this, videoType, s, s2, n, n2, browseAgentCallback));
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchKidsCharacterDetailsTask(this, s, browseAgentCallback));
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoMosTask(this, n, n2, browseAgentCallback));
    }
    
    public void fetchMovieDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchMovieDetailsTask(this, s, s2, browseAgentCallback));
    }
    
    public void fetchNonMemberVideos(final int n, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchNonMemberVideosTask(this, n, b, browseAgentCallback));
    }
    
    public void fetchNotifications(final int n, final int n2, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchNotificationsTask(this, n, n2, b, browseAgentCallback));
    }
    
    public void fetchOfflineGeoPlayability(final List<String> list, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchOfflineGeoPlayabilityTask(this, list, browseAgentCallback));
    }
    
    public void fetchPersonDetail(final String s, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.launchTask(new FetchPersonDetail(this, s, browseAgentCallback, s2));
    }
    
    public void fetchPersonRelated(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchPersonRelated(this, s, browseAgentCallback));
    }
    
    public void fetchPostPlayVideos(final String s, final VideoType videoType, final PostPlayRequestContext postPlayRequestContext, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchPostPlayVideosTask(this, s, videoType, postPlayRequestContext, browseAgentCallback));
    }
    
    public void fetchScenePosition(final VideoType videoType, final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchScenePositionTask(this, videoType, s, s2, browseAgentCallback));
    }
    
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSeasonDetailsTask(this, s, browseAgentCallback));
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSeasonsTask(this, s, n, n2, browseAgentCallback));
    }
    
    public void fetchShowDetails(final String s, final String s2, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchShowDetailsTask(this, s, s2, b, b2, b3, browseAgentCallback));
    }
    
    public void fetchSimilarVideos(final Falkor$SimilarRequestType falkor$SimilarRequestType, final String s, final int n, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSimilarVideosTask(this, falkor$SimilarRequestType, s, n, s2, browseAgentCallback));
    }
    
    public void fetchTask(final CmpTaskDetails cmpTaskDetails, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new CmpTaskWrapper(this, cmpTaskDetails, browseAgentCallback));
    }
    
    public void fetchVideoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchVideoSummaryTask(this, s, browseAgentCallback));
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final boolean b, final boolean b2, final boolean b3, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoType.BILLBOARD.equals(loMo.getType())) {
            this.launchTask(new FetchBillboardVideosTask(this, n, n2, b, browseAgentCallback));
            return;
        }
        if (LoMoType.DISCOVERY_ROW.equals(loMo.getType())) {
            this.launchTask(new FetchDiscoveryVideosTask(this, loMo.getId(), n, n2, b, browseAgentCallback));
            return;
        }
        this.launchTask(new FetchVideosTask(this, loMo, n, n2, b, b2, b3, browseAgentCallback));
    }
    
    public void flushCaches() {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.v("CachedModelProxy", "Flushing caches...");
            }
            this.lastPrefetchFromVideo = -1;
            this.lastPrefetchToVideo = -1;
            if (!(this.root instanceof Flushable)) {
                return;
            }
            final Flushable flushable = (Flushable)this.root;
            try {
                if (Log.isLoggable()) {
                    Log.v("CachedModelProxy", "Flushing root");
                }
                flushable.flush();
            }
            catch (IOException ex) {
                Log.handleException("CachedModelProxy", ex);
            }
        }
    }
    
    public void forceFetchFromLocalCache(final boolean force_CMP_TO_LOCAL_CACHE) {
        Log.w("CachedModelProxy", String.format("forcing CMP fetch behavior to useLocalCacheOnly:%b", force_CMP_TO_LOCAL_CACHE));
        CachedModelProxy.FORCE_CMP_TO_LOCAL_CACHE = force_CMP_TO_LOCAL_CACHE;
    }
    
    @Override
    public CachedModelProxy$GetResult get(final Collection<PQL> collection) {
        final CachedModelProxy$GetResult cachedModelProxy$GetResult;
        synchronized (this) {
            cachedModelProxy$GetResult = new CachedModelProxy$GetResult(collection);
            final Iterator<PQL> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.get(iterator.next(), this.root, 0, cachedModelProxy$GetResult);
            }
        }
        // monitorexit(this)
        return cachedModelProxy$GetResult;
    }
    
    public <I extends FalkorObject> List<I> getAllItemsAsList(final PQL pql) {
        final ArrayList<FalkorObject> list;
        synchronized (this) {
            list = (ArrayList<FalkorObject>)new ArrayList<I>();
            for (final PQL pql2 : pql.explode()) {
                final Object value = this.getValue(pql2);
                if (value instanceof FalkorObject) {
                    final FalkorObject falkorObject = (I)value;
                    if (Falkor.ENABLE_VERBOSE_LOGGING) {
                        Log.v("CachedModelProxy", "got falkor object - pql: " + pql2 + ", item: " + falkorObject);
                    }
                    list.add(falkorObject);
                }
            }
        }
        // monitorexit(this)
        return (List<I>)list;
    }
    
    public Context getContext() {
        return (Context)this.getService();
    }
    
    public String getCurrLolomoId() {
        while (true) {
            synchronized (this) {
                final Ref ref = (Ref)this.getValue(PQL.create("lolomo"));
                String s;
                if (ref == null) {
                    s = null;
                }
                else {
                    final PQL refPath = ref.getRefPath();
                    if (refPath == null) {
                        return null;
                    }
                    if (refPath.getNumKeySegments() < 2) {
                        return null;
                    }
                    s = (String)refPath.getKeySegments().get(1);
                }
                return s;
            }
            return null;
        }
    }
    
    public Pair<LoMo, String> getCurrLomoByType(final LoMoType loMoType) {
        while (true) {
            synchronized (this) {
                final Ref ref = (Ref)this.getValue(PQL.create("lolomo"));
                Pair pair;
                if (ref == null) {
                    pair = null;
                }
                else {
                    final BranchMap branchMap = (BranchMap)this.getValue(ref.getRefPath());
                    if (branchMap == null) {
                        pair = null;
                    }
                    else {
                        LoMo loMo = null;
                        Block_8: {
                            for (final String s : branchMap.keySet()) {
                                if (TextUtils.isDigitsOnly((CharSequence)s)) {
                                    loMo = (LoMo)this.getValue(ref.getRefPath().append(PQL.create(s, "summary")));
                                    if (loMo != null && loMo.getType() == loMoType) {
                                        break Block_8;
                                    }
                                    continue;
                                }
                            }
                            return null;
                        }
                        final String s;
                        pair = new Pair((Object)loMo, (Object)s);
                    }
                }
                return (Pair<LoMo, String>)pair;
            }
            Pair pair = null;
            return (Pair<LoMo, String>)pair;
        }
    }
    
    public String getCurrentProfileGuidOrNull() {
        String profileGuid = null;
        final NetflixService service = this.getService();
        UserProfile currentProfile;
        if (service != null) {
            currentProfile = service.getCurrentProfile();
        }
        else {
            currentProfile = null;
        }
        if (currentProfile != null) {
            profileGuid = currentProfile.getProfileGuid();
        }
        return profileGuid;
    }
    
    public List<GenreList> getGenreList() {
        final Object value = this.getValue(PQL.create("genreList"));
        if (value != null) {
            return ((Sentinel<List<GenreList>>)value).getValue();
        }
        return null;
    }
    
    @Override
    public <I extends FalkorObject> List<I> getItemsAsList(final PQL pql) {
        return this.getItemsAsList(Collections.singleton(pql));
    }
    
    @Override
    public <I extends FalkorObject> List<I> getItemsAsList(final Collection<PQL> collection) {
        final LinkedHashSet<I> set;
        synchronized (this) {
            set = new LinkedHashSet<I>();
            final Iterator<PQL> iterator = collection.iterator();
            while (iterator.hasNext()) {
                for (final PQL pql : iterator.next().explode()) {
                    final Object value = this.getValue(pql);
                    if (value instanceof FalkorObject) {
                        final FalkorObject falkorObject = (I)value;
                        if (Falkor.ENABLE_VERBOSE_LOGGING) {
                            Log.v("CachedModelProxy", "got falkor object - pql: " + pql + ", item: " + falkorObject);
                        }
                        set.add((I)falkorObject);
                    }
                }
            }
        }
        final ArrayList list = new ArrayList<I>(set.size());
        final Iterator<Object> iterator3 = set.iterator();
        while (iterator3.hasNext()) {
            list.add((I)iterator3.next());
        }
        if (Falkor.ENABLE_VERBOSE_LOGGING) {
            Log.v("CachedModelProxy", "Items size: " + list.size());
        }
        // monitorexit(this)
        return (List<I>)list;
    }
    
    public JsonParser getJsonParser() {
        return this.jsonParser;
    }
    
    public int getLastPrefetchFromVideo() {
        return this.lastPrefetchFromVideo;
    }
    
    public int getLastPrefetchToVideo() {
        return this.lastPrefetchToVideo;
    }
    
    public <LT extends LoMo> List<LT> getLists(final Collection<PQL> collection) {
        while (true) {
            // monitorenter(this)
            int n = 0;
            while (true) {
                Label_0238: {
                    int listPos = 0;
                    Label_0234: {
                        Label_0231: {
                            try {
                                final ArrayList<LT> list = new ArrayList<LT>();
                                final Iterator<PQL> iterator = collection.iterator();
                                while (iterator.hasNext()) {
                                    final Iterator<Object> iterator2 = iterator.next().explode().iterator();
                                    listPos = n;
                                    n = listPos;
                                    if (iterator2.hasNext()) {
                                        final PQL pql = iterator2.next();
                                        final Object value = this.getValue(pql);
                                        if (!(value instanceof LoMo)) {
                                            break Label_0231;
                                        }
                                        final LoMo loMo = (LT)value;
                                        loMo.setListPos(listPos);
                                        list.add((LT)loMo);
                                        if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                            Log.v("CachedModelProxy", "got lomo - pql: " + pql + ", lomo: " + loMo.getTitle() + ", order: " + loMo.getListPos());
                                        }
                                        break Label_0234;
                                    }
                                }
                                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                    Log.v("CachedModelProxy", "Lomos size: " + list.size());
                                }
                                return list;
                            }
                            finally {
                            }
                            // monitorexit(this)
                        }
                        break Label_0238;
                    }
                    ++listPos;
                }
                continue;
            }
        }
    }
    
    public <I extends MementoVideoSwatch> List<I> getMementoAsSwatchAsList(final PQL pql) {
        final ArrayList<MementoVideoSwatch> list;
        synchronized (this) {
            list = (ArrayList<MementoVideoSwatch>)new ArrayList<I>();
            for (final PQL pql2 : pql.explode()) {
                final Object value = this.getValue(pql2);
                if (value instanceof MementoVideoSwatch) {
                    final MementoVideoSwatch mementoVideoSwatch = (I)value;
                    if (Falkor.ENABLE_VERBOSE_LOGGING) {
                        Log.v("CachedModelProxy", "got MementoVideoSwatch object - pql: " + pql2 + ", item: " + mementoVideoSwatch);
                    }
                    list.add(mementoVideoSwatch);
                }
            }
        }
        // monitorexit(this)
        return (List<I>)list;
    }
    
    public T getRoot() {
        return this.root;
    }
    
    @Override
    public ServiceProvider getServiceProvider() {
        return this.serviceProvider;
    }
    
    @Override
    public Object getValue(final PQL pql) {
        // monitorenter(this)
        Label_0013: {
            if (pql == null) {
                break Label_0013;
            }
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0321: {
                        try {
                            Object o;
                            if (pql.isEmpty()) {
                                if (Log.isLoggable()) {
                                    Log.w("CachedModelProxy", "Empty pql - leaving getValue() early");
                                }
                                o = null;
                            }
                            else {
                                if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                    Log.v("CachedModelProxy", "getValue() pql: " + pql);
                                }
                                Object o2 = this.root;
                                final BranchNode branchNode = (BranchNode)o2;
                                final List<Object> keySegments = pql.getKeySegments();
                                final int size = keySegments.size();
                                n = 0;
                                o = o2;
                                if (n < size) {
                                    final String value = keySegments.get(n);
                                    if (value == null) {
                                        break Label_0321;
                                    }
                                    final Object o3 = o2 = branchNode.get(value);
                                    if (Falkor.ENABLE_VERBOSE_LOGGING) {
                                        o2 = new StringBuilder().append("getValue() for key: ").append(value.toString()).append(" currentValue: ");
                                        String simpleName;
                                        if (o3 == null) {
                                            simpleName = "null";
                                        }
                                        else {
                                            simpleName = ((BranchNode)o3).getClass().getSimpleName();
                                        }
                                        Log.v("CachedModelProxy", ((StringBuilder)o2).append(simpleName).toString());
                                        o2 = o3;
                                    }
                                    while (o2 instanceof Ref) {
                                        o2 = (o = o2);
                                        if (n == size - 1) {
                                            return o;
                                        }
                                        o2 = ((Ref)o2).getValue(this);
                                    }
                                    if (o2 instanceof FalkorObject) {
                                        o = o2;
                                        if (n >= size - 2) {
                                            return o;
                                        }
                                    }
                                    if (o2 instanceof BranchNode) {
                                        final BranchNode branchNode2 = (BranchNode)o2;
                                        break Label_0321;
                                    }
                                    o = o2;
                                    if (!(o2 instanceof Exception)) {
                                        final boolean b = o2 instanceof Undefined;
                                        o = o2;
                                        if (b) {
                                            o = o2;
                                        }
                                    }
                                }
                            }
                            return o;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    ++n;
                    final BranchNode branchNode3;
                    final BranchNode branchNode = branchNode3;
                    continue;
                }
            }
        }
    }
    
    public FalkorObject getVideo(final PQL pql) {
        synchronized (this) {
            for (final PQL pql2 : pql.explode()) {
                final Object value = this.getValue(pql2);
                if (value instanceof FalkorObject) {
                    FalkorObject falkorObject2;
                    final FalkorObject falkorObject = falkorObject2 = (FalkorObject)value;
                    if (Falkor.ENABLE_VERBOSE_LOGGING) {
                        Log.v("CachedModelProxy", "got video - pql: " + pql2 + ", video: " + falkorObject);
                        falkorObject2 = falkorObject;
                    }
                    return falkorObject2;
                }
            }
            if (Log.isLoggable()) {
                Log.d("CachedModelProxy", "Couldn't find video in cache: " + pql);
            }
            return null;
        }
    }
    
    public FalkorVolleyWebClient getWebClient() {
        return this.webClient;
    }
    
    public void invalidate(final PQL pql) {
        while (true) {
            final Object value;
            Label_0146: {
                synchronized (this) {
                    value = this.getValue(pql.slice(0, pql.getNumKeySegments() - 1));
                    if (value == null) {
                        if (Log.isLoggable()) {
                            Log.d("CachedModelProxy", "Can't invalidate node because it is null: " + pql);
                        }
                    }
                    else {
                        if (!(value instanceof BranchNode)) {
                            break Label_0146;
                        }
                        final String value2 = String.valueOf(pql.getKeySegments().get(pql.getNumKeySegments() - 1));
                        if (Log.isLoggable()) {
                            Log.d("CachedModelProxy", "Invalidating at BranchNode: " + ((BranchNode)value).getClass() + ", node key: " + value2);
                        }
                        ((BranchNode)value).remove(value2);
                    }
                    return;
                }
            }
            final Throwable t;
            if (value instanceof Ref) {
                if (Log.isLoggable()) {
                    Log.d("CachedModelProxy", "Invalidating ref path for pql: " + t);
                }
                ((Ref)value).setRefPath(null);
                return;
            }
            if (Log.isLoggable()) {
                Log.w("CachedModelProxy", "Don't know how to invalidate node: " + ((Ref)value).getClass() + ", pql: " + t);
            }
        }
    }
    
    public void invalidateEpisodes(final String s, final VideoType videoType) {
        this.invalidate(PQL.create(videoType.getValue(), s, "episodes"));
    }
    
    public void logBillboardActivity(final Video video, final BillboardInteractionType billboardInteractionType, final Map<String, String> map) {
        this.launchTask(new LogBillboardActivityTask(this, video, billboardInteractionType, map));
    }
    
    public void logHandledException(final String s) {
        this.getService().getClientLogging().getErrorLogging().logHandledException(s);
    }
    
    public void logPostPlayImpression(final String s, final VideoType videoType, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new LogPostPlayImpression(this, s, videoType, s2, browseAgentCallback));
    }
    
    public void markNotificationAsRead(final IrisNotificationSummary irisNotificationSummary, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new MarkNotificationAsReadTask(this, irisNotificationSummary, browseAgentCallback));
    }
    
    public void markNotificationsAsRead(final List<IrisNotificationSummary> list, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new MarkNotificationAsReadTask(this, list, browseAgentCallback));
    }
    
    public void onCWVideosFetched(final List<CWVideo> list) {
        this.getService().getBookmarkStore().onCWVideosFetched(list, this.getCurrentProfileGuidOrNull());
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new PrefetchGenreLoLoMoTask(this, s, n2, n4, b, browseAgentCallback));
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int lastPrefetchFromVideo, final int lastPrefetchToVideo, final int n3, final int n4, final boolean b, final boolean b2, final boolean b3, final boolean b4, final BrowseAgentCallback browseAgentCallback) {
        this.lastPrefetchFromVideo = lastPrefetchFromVideo;
        this.lastPrefetchToVideo = lastPrefetchToVideo;
        this.launchTask(new PrefetchLoLoMoTask(this, n2, lastPrefetchToVideo, n4, 9, b, b2, b4, browseAgentCallback));
    }
    
    public void prefetchVideoDetailsFromQueue() {
        while (!DPPrefetchABTestUtils.isPrefetchQueueEmpty() && DPPrefetchABTestUtils.getPrefetchCounter() < 2) {
            DPPrefetchABTestUtils.incrementPrefetchCounter();
            final Video nextPrefetchVideo = DPPrefetchABTestUtils.getNextPrefetchVideo();
            if (nextPrefetchVideo != null) {
                if (Log.isLoggable()) {
                    Log.d("CachedModelProxy", "Prefetch DP request for " + nextPrefetchVideo.getId() + ": " + nextPrefetchVideo.getTitle());
                }
                this.launchTask(new PrefetchVideoListDetailsTask(this, Collections.singletonList(nextPrefetchVideo), DPPrefetchABTestUtils.removePrefetchDPCallback(nextPrefetchVideo)));
            }
            else {
                DPPrefetchABTestUtils.decrementPrefetchCounter();
            }
        }
    }
    
    public void prefetchVideoListDetails(final List<? extends Video> list, final BrowseAgentCallback browseAgentCallback) {
        final boolean prefetchQueueEmpty = DPPrefetchABTestUtils.isPrefetchQueueEmpty();
        DPPrefetchABTestUtils.addToQueue(list, browseAgentCallback);
        if (prefetchQueueEmpty) {
            this.prefetchVideoDetailsFromQueue();
        }
    }
    
    public void refreshCw() {
        if (!this.doesCwExist()) {
            Log.d("CachedModelProxy", "Can't refresh CW ");
            return;
        }
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.CONTINUE_WATCHING);
        this.launchTask(new RefreshCwTask(this, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second));
    }
    
    public void refreshDiscoveryRow() {
        if (!this.doesDiscoveryRowExist()) {
            Log.d("CachedModelProxy", "Can't refresh Discovery ");
            return;
        }
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.DISCOVERY_ROW);
        this.launchTask(new RefreshDiscoveryTask(this, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second));
    }
    
    public void refreshIq() {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.INSTANT_QUEUE);
        if (StringUtils.isEmpty(currLolomoId)) {
            Log.d("CachedModelProxy", "Can't refresh IQ - lolomoId is empty");
            return;
        }
        if (StringUtils.isEmpty((String)currLomoInfo.first)) {
            Log.d("CachedModelProxy", "Can't refresh IQ - lomo id is empty");
            return;
        }
        if (String.valueOf(-1).equals(currLomoInfo.second)) {
            Log.d("CachedModelProxy", "Can't refresh IQ - lomo index is invalid");
            return;
        }
        this.launchTask(new RefreshIqTask(this, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second));
    }
    
    public void refreshPopularTitlesLomo() {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.POPULAR_TITLES);
        if (StringUtils.isEmpty(currLolomoId)) {
            Log.d("CachedModelProxy", "Can't refresh Popular Titles - lolomoId is empty");
            return;
        }
        if (StringUtils.isEmpty((String)currLomoInfo.first)) {
            Log.d("CachedModelProxy", "Can't refresh Popular Titles - lomo id is empty");
            return;
        }
        if (String.valueOf(-1).equals(currLomoInfo.second)) {
            Log.d("CachedModelProxy", "Can't refresh Popular Titles - lomo index is invalid");
            return;
        }
        this.launchTask(new RefreshPopularTitlesTask(this, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second));
    }
    
    public void removeFromQueue(final String s, final VideoType videoType, final String s2, final BrowseAgentCallback browseAgentCallback) {
        final String currLolomoId = this.getCurrLolomoId();
        final DataUtil$StringPair currLomoInfo = this.getCurrLomoInfo(LoMoType.INSTANT_QUEUE);
        this.launchTask(new RemoveFromQueueTask(this, s, videoType, currLolomoId, (String)currLomoInfo.first, (String)currLomoInfo.second, s2, browseAgentCallback));
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchSearchResultsTask(this, s, browseAgentCallback));
    }
    
    public void sendDetailPageReloadBroadcast() {
        this.sendDetailPageReloadBroadcast(this.getContext());
    }
    
    public void serialize(final Writer writer) {
        this.write(new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting().setVersion(1.0).addSerializationExclusionStrategy(new CachedModelProxy$SuperclassExclusionStrategy(this, null)).addDeserializationExclusionStrategy(new CachedModelProxy$SuperclassExclusionStrategy(this, null)).create(), new JsonWriter(writer));
    }
    
    public void setVideoRating(final String s, final VideoType videoType, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new SetVideoRatingTask(this, s, videoType, n, n2, browseAgentCallback));
    }
    
    @Override
    public String toString() {
        synchronized (this) {
            return "CachedModelProxy cache: " + this.root.toString();
        }
    }
    
    public void updateBookmarkIfExists(final String s, final Video$Bookmark video$Bookmark) {
        this.getService().getBookmarkStore().updateBookmarkIfExists(s, video$Bookmark, this.getCurrentProfileGuidOrNull());
    }
    
    public void updateBookmarkPosition(final Asset asset) {
        if (asset == null) {
            Log.w("CachedModelProxy", "Can't update bookmark position - asset is null");
        }
        else {
            final int playbackBookmark = asset.getPlaybackBookmark();
            if (Falkor.ENABLE_VERBOSE_LOGGING) {
                Log.v("CachedModelProxy", "Updating video positions for asset: " + asset.getTitle() + ", playable bookmark: " + playbackBookmark);
            }
            if (playbackBookmark <= 0) {
                if (Log.isLoggable()) {
                    Log.w("CachedModelProxy", "updateBookmarkPosition - bookmarkPos <= 0");
                }
            }
            else {
                if (StringUtils.isEmpty(asset.getPlayableId())) {
                    Log.w("CachedModelProxy", String.format("asset with null playableId, asset:%s", asset));
                    return;
                }
                String s;
                if (asset.isEpisode()) {
                    s = VideoType.EPISODE.getValue();
                }
                else {
                    s = VideoType.MOVIE.getValue();
                }
                final FalkorVideo falkorVideo = (FalkorVideo)this.getVideo(PQL.create(s, asset.getPlayableId(), "bookmark"));
                if (falkorVideo == null) {
                    if (Log.isLoggable()) {
                        Log.w("CachedModelProxy", "updateBookmarkPosition - video is null, id: " + asset.getPlayableId());
                    }
                }
                else {
                    final Video$Bookmark bookmark = falkorVideo.getBookmark();
                    if (bookmark != null) {
                        bookmark.setBookmarkPosition(playbackBookmark);
                        bookmark.setLastModified(System.currentTimeMillis());
                        return;
                    }
                    if (Log.isLoggable()) {
                        Log.w("CachedModelProxy", "updateBookmarkPosition - bookmark is null, video id: " + asset.getPlayableId());
                    }
                }
            }
        }
    }
    
    public void updateExpiredContentAdvisoryStatus(final String s, final ExpiringContentAdvisory$ContentAction expiringContentAdvisory$ContentAction) {
        this.launchTask(new UpdateExpiryAdvisoryStatusTask(this, s, expiringContentAdvisory$ContentAction));
    }
    
    public void updateInQueueStatus(final VideoType videoType, final String s, final boolean b) {
        synchronized (this) {
            final FalkorVideo falkorVideo = (FalkorVideo)this.getValue(PQL.create(videoType.getValue(), s, "summary"));
            if (falkorVideo != null) {
                Log.v("CachedModelProxy", "Setting cached inQueue value to: %b, for video: %s", b, s);
                falkorVideo.set("inQueue", (Object)new Video$InQueue(b));
            }
        }
    }
}
