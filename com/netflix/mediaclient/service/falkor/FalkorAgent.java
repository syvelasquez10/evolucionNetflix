// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.falkor.request.PrefetchHomeLoLoMoRequest;
import com.netflix.mediaclient.service.falkor.request.FetchVideosRequest;
import java.util.ArrayList;
import java.util.Collection;
import com.netflix.mediaclient.service.falkor.request.FetchLoMosRequest;
import com.netflix.falkor.PQL;
import java.util.List;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.falkor.RequestProvider;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorWebClientRequest;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import com.netflix.falkor.CachedModelProxy;
import com.netflix.model.Root;
import com.netflix.mediaclient.service.ServiceAgent;

public class FalkorAgent extends ServiceAgent implements BrowseAgentInterface
{
    private static final int MOVIE_DETAILS_MAX_SIMILARS = 50;
    private static final int PREFETCH_BILLBOARD_VIDEO_INDEX = 9;
    private static final String TAG = "FalkorAgent";
    private Root cache;
    private CachedModelProxy<Root> cacheProxy;
    private FalcorVolleyWebClient webClient;
    
    private BrowseAgentCallback createHandlerWrapper(final BrowseAgentCallback browseAgentCallback) {
        return new PostToHandlerCallbackWrapper(this.getMainHandler(), browseAgentCallback);
    }
    
    private void executeRequest(final FalkorWebClientRequest<?> falkorWebClientRequest) {
        this.webClient.sendRequest(falkorWebClientRequest, ApiEndpointRegistry.ResponsePathFormat.GRAPH);
    }
    
    private void launchTask(final Runnable runnable) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", "Launching task: " + runnable.getClass().getSimpleName());
        }
        new BackgroundTask().execute(runnable);
    }
    
    private void logVerbose(final String s) {
        if (Log.isLoggable("FalkorAgent", 2)) {
            Log.v("FalkorAgent", s);
        }
    }
    
    public void addToQueue(final String s, final int n, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    @Override
    public void destroy() {
        super.destroy();
    }
    
    public void doInit() {
        this.cache = new Root();
        this.cacheProxy = new CachedModelProxy<Root>(null, this.cache);
        this.webClient = (FalcorVolleyWebClient)this.getResourceFetcher().getApiNextWebClient();
        this.initCompleted(CommonStatus.OK);
    }
    
    public void dumpHomeLoLoMosAndVideos(final String s, final String s2) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void dumpHomeLoLoMosAndVideosToLog() {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void dumpHomeLoMos() {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    @Override
    public void fetchEpisodeDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchEpisodes(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchGenreVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchGenres(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
        this.launchTask(new FetchVideosTask(loMo, n, n2, browseAgentCallback));
    }
    
    public void fetchKidsCharacterDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchLoLoMoSummary(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
        this.launchTask(new FetchLoMosTask(n, n2, browseAgentCallback));
    }
    
    @Override
    public void fetchMovieDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    @Override
    public void fetchPostPlayVideos(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    @Override
    public void fetchSeasonDetails(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchSeasons(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    @Override
    public void fetchShowDetails(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchSimilarVideosForPerson(final String s, final int n, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchSimilarVideosForQuerySuggestion(final String s, final int n, final BrowseAgentCallback browseAgentCallback, final String s2) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
        if (LoMoType.BILLBOARD.equals(loMo.getType())) {
            this.logVerbose("fetchVideos for billboards not implemented yet");
            return;
        }
        this.launchTask(new FetchVideosTask(loMo, n, n2, browseAgentCallback));
    }
    
    public void flushCaches() {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void hideVideo(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void logBillboardActivity(final Video video, final BrowseAgent.BillboardActivityType billboardActivityType) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final boolean b, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
        this.launchTask(new PrefetchLoLoMoTask(n2, n4, n6, 9, b, browseAgentCallback));
    }
    
    public void refreshCW() {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void refreshEpisodesData(final Asset asset) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void refreshIQ() {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void removeFromQueue(final String s, final String s2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void searchNetflix(final String s, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void setVideoRating(final String s, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    public void updateCachedVideoPosition(final Asset asset) {
        this.logVerbose(LogUtils.getCurrMethodName());
    }
    
    private class FetchLoMosTask extends FetchTask
    {
        private final int fromLomo;
        private final int toLomo;
        
        public FetchLoMosTask(final int fromLomo, final int toLomo, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.fromLomo = fromLomo;
            this.toLomo = toLomo;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            list.add(PQL.create("lolomo", PQL.range(this.fromLomo, this.toLomo), "summary"));
        }
        
        @Override
        protected FalkorWebClientRequest<?> createRemoteRequest(final CachedModelProxy.GetResult getResult) {
            return new FetchLoMosRequest(FalkorAgent.this.getContext(), FalkorAgent.this.cacheProxy, getResult, ((FetchTask)this).getCallback());
        }
        
        @Override
        protected void fetchResultsAndCallback(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy.GetResult getResult) {
            browseAgentCallback.onLoMosFetched(FalkorAgent.this.cacheProxy.getLomos(getResult.pqls), CommonStatus.OK);
        }
    }
    
    private abstract class FetchTask implements Runnable
    {
        private final BrowseAgentCallback callback;
        
        protected FetchTask(final BrowseAgentCallback callback) {
            this.callback = callback;
        }
        
        protected abstract void buildPqlList(final List<PQL> p0);
        
        protected abstract FalkorWebClientRequest<?> createRemoteRequest(final CachedModelProxy.GetResult p0);
        
        protected abstract void fetchResultsAndCallback(final BrowseAgentCallback p0, final CachedModelProxy.GetResult p1);
        
        protected BrowseAgentCallback getCallback() {
            return this.callback;
        }
        
        @Override
        public final void run() {
            final long nanoTime = System.nanoTime();
            LogUtils.logCurrentThreadName("FalkorAgent", "running" + this.getClass().getSimpleName());
            final ArrayList<PQL> list = new ArrayList<PQL>();
            this.buildPqlList(list);
            final CachedModelProxy.GetResult value = FalkorAgent.this.cacheProxy.get(list);
            value.printPaths("FalkorAgent");
            if (value.hasMissingPaths()) {
                FalkorAgent.this.executeRequest(this.createRemoteRequest(value));
            }
            else {
                this.fetchResultsAndCallback(FalkorAgent.this.createHandlerWrapper(this.callback), value);
            }
            if (Log.isLoggable("FalkorAgent", 2)) {
                Log.v("FalkorAgent", this.getClass().getSimpleName() + " run() time ms: " + (System.nanoTime() - nanoTime) / 1000000L);
            }
        }
    }
    
    private class FetchVideosTask extends FetchTask
    {
        private final int fromVideo;
        private final LoMo lomo;
        private final int toVideo;
        
        public FetchVideosTask(final LoMo lomo, final int fromVideo, final int toVideo, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.lomo = lomo;
            this.fromVideo = fromVideo;
            this.toVideo = toVideo;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            list.add(PQL.create("lists", this.lomo.getId(), PQL.range(this.fromVideo, this.toVideo), "summary"));
        }
        
        @Override
        protected FalkorWebClientRequest<?> createRemoteRequest(final CachedModelProxy.GetResult getResult) {
            return new FetchVideosRequest(FalkorAgent.this.getContext(), FalkorAgent.this.cacheProxy, getResult, ((FetchTask)this).getCallback());
        }
        
        @Override
        protected void fetchResultsAndCallback(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy.GetResult getResult) {
            browseAgentCallback.onVideosFetched(FalkorAgent.this.cacheProxy.getVideos(getResult.pqls), CommonStatus.OK);
        }
    }
    
    private class PrefetchLoLoMoTask extends FetchTask
    {
        private final boolean includeExtraCharacters;
        private final int toBBVideo;
        private final int toCWVideo;
        private final int toLomo;
        private final int toVideo;
        
        public PrefetchLoLoMoTask(final int toLomo, final int toVideo, final int toCWVideo, final int toBBVideo, final boolean includeExtraCharacters, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.toLomo = toLomo;
            this.toVideo = toVideo;
            this.toCWVideo = toCWVideo;
            this.toBBVideo = toBBVideo;
            this.includeExtraCharacters = includeExtraCharacters;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            list.add(PQL.create("lolomo", PQL.range(this.toLomo), "summary"));
            list.add(PQL.create("lolomo", PQL.range(this.toLomo), PQL.range(this.toVideo), "summary"));
            list.add(PQL.create("lolomo", "continueWatching", PQL.range(this.toCWVideo), PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "socialEvidence")));
            list.add(PQL.create("lolomo", "continueWatching", PQL.range(this.toCWVideo), "episodes", "current", PQL.array("detail", "bookmark")));
            list.add(PQL.create("lolomo", "continueWatching", PQL.range(this.toCWVideo), "similars", PQL.range(50), "summary"));
            list.add(PQL.create("lolomo", "continueWatching", PQL.range(this.toCWVideo), "similars", "summary"));
            list.add(PQL.create("lolomo", "billboard", PQL.range(this.toBBVideo), PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "socialEvidence")));
            list.add(PQL.create("lolomo", "billboard", PQL.range(this.toBBVideo), "episodes", "current", PQL.array("detail", "bookmark")));
            list.add(PQL.create("lolomo", "billboard", PQL.range(this.toBBVideo), "similars", PQL.range(50), "summary"));
            list.add(PQL.create("lolomo", "billboard", PQL.range(this.toBBVideo), "similars", "summary"));
            if (this.includeExtraCharacters) {
                final int n = this.toVideo + 1;
                list.add(PQL.create("lolomo", "characters", PQL.range(n, this.toVideo + n), "summary"));
            }
        }
        
        @Override
        protected FalkorWebClientRequest<?> createRemoteRequest(final CachedModelProxy.GetResult getResult) {
            return new PrefetchHomeLoLoMoRequest(FalkorAgent.this.getContext(), FalkorAgent.this.cacheProxy, getResult, ((FetchTask)this).getCallback());
        }
        
        @Override
        protected void fetchResultsAndCallback(final BrowseAgentCallback browseAgentCallback, final CachedModelProxy.GetResult getResult) {
            browseAgentCallback.onLoLoMoPrefetched(CommonStatus.OK);
        }
    }
}
