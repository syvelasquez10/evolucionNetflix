// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.Collections;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.model.branches.FalkorVideo;
import com.netflix.mediaclient.service.NetflixService;
import java.io.IOException;
import java.io.Flushable;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.servicemgr.model.JsonPopulator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.android.app.BackgroundTask;
import java.util.LinkedHashSet;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.genre.GenreList;
import java.util.Iterator;
import java.util.Map;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.mediaclient.service.browse.PostToHandlerCallbackWrapper;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;
import java.util.Collection;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;
import android.os.Looper;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClient;
import android.os.Handler;
import com.google.gson.JsonParser;

public class CachedModelProxy<T extends BranchNode> implements ModelProxy<T>
{
    private static final boolean LOG_VERBOSE = false;
    private static final int MOVIE_DETAILS_MAX_SIMILARS = 24;
    private static final int PREFETCH_BILLBOARD_VIDEO_INDEX = 9;
    private static final String TAG = "CachedModelProxy";
    private static final String VALUE = "value";
    private final JsonParser jsonParser;
    private final Handler mainHandler;
    private final T root;
    private final ServiceProvider serviceProvider;
    private final FalcorVolleyWebClient webClient;
    
    public CachedModelProxy(final ServiceProvider serviceProvider, final T root, final FalcorVolleyWebClient webClient) {
        this.jsonParser = new JsonParser();
        this.serviceProvider = serviceProvider;
        this.root = root;
        this.webClient = webClient;
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
    
    private static void buildBillboardPql(final List<PQL> list, final int n, final int n2) {
        list.add(PQL.create("lolomo", "billboard", PQL.range(n2), PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "socialEvidence")));
        list.add(PQL.create("lolomo", "billboard", PQL.range(n2), "episodes", "current", PQL.array("detail", "bookmark")));
        list.add(PQL.create("lolomo", "billboard", PQL.range(n2), "similars", PQL.range(24), "summary"));
        list.add(PQL.create("lolomo", "billboard", PQL.range(n2), "similars", "summary"));
    }
    
    private static void buildCwPql(final List<PQL> list, final int n, final int n2) {
        list.add(PQL.create("lolomo", "continueWatching", PQL.range(n, n2), PQL.array("summary", "detail", "rating", "inQueue", "bookmark", "bookmarkStill", "socialEvidence")));
        list.add(PQL.create("lolomo", "continueWatching", PQL.range(n, n2), "episodes", "current", PQL.array("detail", "bookmark")));
        list.add(PQL.create("lolomo", "continueWatching", PQL.range(n, n2), "similars", PQL.range(24), "summary"));
        list.add(PQL.create("lolomo", "continueWatching", PQL.range(n, n2), "similars", "summary"));
    }
    
    private BrowseAgentCallback createHandlerWrapper(final BrowseAgentCallback browseAgentCallback) {
        return new PostToHandlerCallbackWrapper(this.mainHandler, browseAgentCallback);
    }
    
    private void executeRequest(final FalcorVolleyWebClientRequest<?> falcorVolleyWebClientRequest) {
        this.webClient.sendRequest(falcorVolleyWebClientRequest, ApiEndpointRegistry.ResponsePathFormat.GRAPH);
    }
    
    private void get(final PQL pql, final Object o, final int n, final GetResult getResult) {
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "get from path: " + pql + ", offset: " + n);
        }
        final int size = pql.getKeySegments().size();
        if (n < size && o == null) {
            getResult.missingPqls.add(pql);
        }
        else if (n == size) {
            if (o != null) {
                getResult.foundPqls.add(pql);
                return;
            }
            getResult.missingPqls.add(pql);
        }
        else {
            if (n > size) {
                throw new IllegalStateException("Offset is invalid");
            }
            if (o instanceof Ref) {
                final Ref ref = (Ref)o;
                final Object hardValue = ref.getHardValue();
                if (hardValue != null) {
                    this.get(ref.getRefPath().append(pql.slice(n)), hardValue, ref.getRefPath().getKeySegments().size(), getResult);
                    return;
                }
                if (ref.getRefPath() == null) {
                    Log.d("CachedModelProxy", "Ref path is null: " + ref.getPath());
                    return;
                }
                this.get(ref.getRefPath().append(pql.slice(n)), this.root, 0, getResult);
            }
            else {
                if (o instanceof Exception || o instanceof Undefined) {
                    getResult.ignoredPqls.add(pql);
                    return;
                }
                final BranchNode branchNode = (BranchNode)o;
                final Map<K, Integer> value = pql.getKeySegments().get(n);
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
                                this.get(pql.replaceKeySegment(n, String.valueOf(i)), o, n, getResult);
                            }
                        }
                        else {
                            this.get(pql, branchNode.get(next.toString()), n + 1, getResult);
                        }
                    }
                }
                else {
                    if (!(value instanceof Map)) {
                        this.get(pql, branchNode.get(value.toString()), n + 1, getResult);
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
                    for (int j = value3; j <= n5; ++j) {
                        this.get(pql.replaceKeySegment(n, String.valueOf(j)), o, n, getResult);
                    }
                }
            }
        }
    }
    
    private List<GenreList> getGenreList() {
        return (List<GenreList>)this.getValue(PQL.create("genreList"));
    }
    
    private List<LoMo> getLomos(final Collection<PQL> collection) {
        int n = 0;
        final ArrayList<LoMo> list = new ArrayList<LoMo>();
        final Iterator<PQL> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final Iterator<Object> iterator2 = iterator.next().explode().iterator();
            int listPos = n;
            while (true) {
                n = listPos;
                if (!iterator2.hasNext()) {
                    break;
                }
                final PQL pql = iterator2.next();
                final Object value = this.getValue(pql);
                if (!(value instanceof LoMo)) {
                    continue;
                }
                final LoMo loMo = (LoMo)value;
                loMo.setListPos(listPos);
                Log.v("CachedModelProxy", "got lomo - pql: " + pql + ", lomo: " + loMo.getTitle() + ", order: " + loMo.getListPos());
                list.add(loMo);
                ++listPos;
            }
        }
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Lomos size: " + list.size());
        }
        return list;
    }
    
    private <I extends Video> List<I> getVideos(final Collection<PQL> collection) {
        final LinkedHashSet<I> set = new LinkedHashSet<I>();
        final Iterator<PQL> iterator = collection.iterator();
        while (iterator.hasNext()) {
            for (final PQL pql : iterator.next().explode()) {
                final Object value = this.getValue(pql);
                if (value instanceof Video) {
                    final Video video = (I)value;
                    Log.v("CachedModelProxy", "got video - pql: " + pql + ", video: " + video.getTitle());
                    set.add((I)video);
                }
            }
        }
        final ArrayList list = new ArrayList<I>(set.size());
        final Iterator<Object> iterator3 = set.iterator();
        while (iterator3.hasNext()) {
            list.add((I)iterator3.next());
        }
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Videos size: " + list.size());
        }
        return (List<I>)list;
    }
    
    private void launchTask(final Runnable runnable) {
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Launching task: " + runnable.getClass().getSimpleName());
        }
        new BackgroundTask().execute(runnable);
    }
    
    private void merge(final JsonObject jsonObject, final BranchNode branchNode) {
        for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            final String s = entry.getKey();
            final Object orCreate = branchNode.getOrCreate(s);
            if (Log.isLoggable("CachedModelProxy", 2)) {
                Log.v("CachedModelProxy", "Curr node: " + branchNode.getClass().getSimpleName() + ", merging: " + s);
            }
            if (orCreate instanceof BranchNode) {
                this.merge(entry.getValue().getAsJsonObject(), (BranchNode)orCreate);
            }
            else if (orCreate instanceof Ref) {
                final Ref ref = (Ref)orCreate;
                final JsonElement jsonElement = entry.getValue();
                if (jsonElement.isJsonArray()) {
                    if (Log.isLoggable("Ref", 2)) {
                        Log.v("Ref", "key now has ref to: " + jsonElement.getAsJsonArray());
                    }
                    ref.setRefPath(PQL.fromJsonArray(jsonElement.getAsJsonArray()));
                }
                else {
                    if (!jsonElement.isJsonObject()) {
                        continue;
                    }
                    if (jsonElement.getAsJsonObject().has("_sentinel")) {
                        if (Log.isLoggable("CachedModelProxy", 2)) {
                            Log.v("CachedModelProxy", "key points to sentinel: " + Undefined.getInstance());
                        }
                        branchNode.set(s, Undefined.getInstance());
                    }
                    else {
                        Log.w("CachedModelProxy", "Don't know how to handle json: " + entry);
                    }
                }
            }
            else {
                if (orCreate == null) {
                    continue;
                }
                if (orCreate instanceof JsonPopulator) {
                    ((JsonPopulator)orCreate).populate(entry.getValue());
                }
                else {
                    if (Log.isLoggable("CachedModelProxy", 5)) {
                        Log.w("CachedModelProxy", "Creating duplicate Leaf object. JsonPopulator should be implemented by: " + ((JsonPopulator)orCreate).getClass());
                    }
                    branchNode.set(s, FalcorParseUtils.createObjectFromJson("CachedModelProxy", entry.getValue(), ((JsonPopulator)orCreate).getClass()));
                }
            }
        }
    }
    
    public void fetchCWVideos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchCwVideosTask(n, n2, browseAgentCallback));
    }
    
    public void fetchGenreList(final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchGenreListTask(browseAgentCallback));
    }
    
    public void fetchIQVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchVideosTask(loMo, n, n2, browseAgentCallback));
    }
    
    public void fetchLoMos(final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new FetchLoMosTask(n, n2, browseAgentCallback));
    }
    
    public void fetchVideos(final LoMo loMo, final int n, final int n2, final BrowseAgentCallback browseAgentCallback) {
        if (LoMoType.BILLBOARD.equals(loMo.getType())) {
            this.launchTask(new FetchBillboardVideosTask(n, n2, browseAgentCallback));
            return;
        }
        this.launchTask(new FetchVideosTask(loMo, n, n2, browseAgentCallback));
    }
    
    public void flushCaches() {
        if (!(this.root instanceof Flushable)) {
            return;
        }
        final Flushable flushable = (Flushable)this.root;
        try {
            flushable.flush();
        }
        catch (IOException ex) {
            Log.handleException("CachedModelProxy", ex);
        }
    }
    
    @Override
    public GetResult get(final Collection<PQL> collection) {
        final GetResult getResult;
        synchronized (this) {
            getResult = new GetResult(collection);
            final Iterator<PQL> iterator = collection.iterator();
            while (iterator.hasNext()) {
                this.get(iterator.next(), this.root, 0, getResult);
            }
        }
        // monitorexit(this)
        return getResult;
    }
    
    protected NetflixService getService() {
        return this.serviceProvider.getService();
    }
    
    @Override
    public ServiceProvider getServiceProvider() {
        return this.serviceProvider;
    }
    
    @Override
    public Object getValue(final PQL pql) {
        // monitorenter(this)
        Label_0017: {
            if (pql == null) {
                break Label_0017;
            }
            while (true) {
                while (true) {
                    int n = 0;
                    Label_0206: {
                        try {
                            Object root;
                            if (pql.isEmpty()) {
                                root = null;
                            }
                            else {
                                BranchNode branchNode = (BranchNode)(root = this.root);
                                if (branchNode instanceof BranchNode) {
                                    final BranchNode branchNode2 = branchNode;
                                    final List<Object> keySegments = pql.getKeySegments();
                                    final int size = keySegments.size();
                                    n = 0;
                                    final Ref ref = (Ref)branchNode;
                                    branchNode = branchNode2;
                                    root = ref;
                                    if (n < size) {
                                        final String value = keySegments.get(n);
                                        if (value == null) {
                                            break Label_0206;
                                        }
                                        Object o;
                                        for (o = branchNode.get(value); o instanceof Ref; o = ((Ref)root).getValue(this)) {
                                            root = o;
                                            if (n == size - 1) {
                                                return root;
                                            }
                                        }
                                        root = o;
                                        if (!(o instanceof FalkorVideo)) {
                                            if (o instanceof BranchNode) {
                                                branchNode = (BranchNode)o;
                                                break Label_0206;
                                            }
                                            root = o;
                                            if (!(o instanceof Exception)) {
                                                final boolean b = o instanceof Undefined;
                                                root = o;
                                                if (b) {
                                                    root = o;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            return root;
                        }
                        finally {
                        }
                        // monitorexit(this)
                    }
                    ++n;
                    continue;
                }
            }
        }
    }
    
    public void merge(final String s) {
        synchronized (this) {
            System.nanoTime();
            this.merge(this.jsonParser.parse(s).getAsJsonObject().getAsJsonObject("value"), this.root);
        }
    }
    
    public void prefetchGenreLoLoMo(final String s, final int n, final int n2, final int n3, final int n4, final BrowseAgentCallback browseAgentCallback) {
        final NetflixService service = this.serviceProvider.getService();
        this.launchTask(new PrefetchGenreLoLoMoTask(s, n2, n4, KidsUtils.isKoPExperience(service.getConfiguration(), service.getCurrentProfile()), browseAgentCallback));
    }
    
    public void prefetchLoLoMo(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final BrowseAgentCallback browseAgentCallback) {
        this.launchTask(new PrefetchLoLoMoTask(n, n2, n4, n6, 9, b, browseAgentCallback));
    }
    
    @Override
    public String toString() {
        return "CachedModelProxy cache: " + this.root.toString();
    }
    
    private abstract class CmpTask implements Runnable
    {
        private final BrowseAgentCallback callback;
        private GetResult getResult;
        
        private CmpTask(final BrowseAgentCallback callback) {
            this.callback = callback;
        }
        
        private FalcorVolleyWebClientRequest createRequest(final List<PQL> list) {
            return new FalcorVolleyWebClientRequest(CachedModelProxy.this.getService()) {
                private final boolean notOnMain = ThreadUtils.assertNotOnMain();
                private final List<String> pqls = DataUtil.createStringListFromList(list);
                
                @Override
                protected List<String> getPQLQueries() {
                    return this.pqls;
                }
                
                @Override
                protected void onFailure(final Status status) {
                    ThreadUtils.assertOnMain();
                    CmpTask.this.handleFailure(CmpTask.this.callback, status);
                }
                
                @Override
                protected void onSuccess(final Object o) {
                    ThreadUtils.assertOnMain();
                }
                
                @Override
                protected Void parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
                    ThreadUtils.assertNotOnMain();
                    try {
                        CachedModelProxy.this.merge(s);
                        CmpTask.this.handleSuccess();
                        return null;
                    }
                    catch (Exception ex) {
                        Log.handleException("CachedModelProxy", ex);
                        throw ex;
                    }
                }
                
                @Override
                protected boolean parsedResponseCanBeNull() {
                    return true;
                }
                
                @Override
                protected boolean shouldMaterializeRequest() {
                    return true;
                }
            };
        }
        
        private void handleFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            this.callbackForFailure(browseAgentCallback, status);
        }
        
        private void handleSuccess() {
            ThreadUtils.assertNotOnMain();
            final BrowseAgentCallback access$400 = CachedModelProxy.this.createHandlerWrapper(this.callback);
            if (this.getResult == null) {
                Log.w("CachedModelProxy", "GetResult is null - shouldn't happen - forcing failure");
                this.handleFailure(access$400, CommonStatus.INTERNAL_ERROR);
                return;
            }
            this.fetchResultsAndCallbackForSuccess(access$400, this.getResult);
        }
        
        protected abstract void buildPqlList(final List<PQL> p0);
        
        protected abstract void callbackForFailure(final BrowseAgentCallback p0, final Status p1);
        
        protected abstract void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback p0, final GetResult p1);
        
        @Override
        public final void run() {
            ThreadUtils.assertNotOnMain();
            final long nanoTime = System.nanoTime();
            LogUtils.logCurrentThreadName("CachedModelProxy", "running" + this.getClass().getSimpleName());
            final ArrayList<PQL> list = new ArrayList<PQL>();
            this.buildPqlList(list);
            (this.getResult = CachedModelProxy.this.get(list)).printPaths("CachedModelProxy");
            if (this.getResult.hasMissingPaths()) {
                CachedModelProxy.this.executeRequest(this.createRequest(this.getResult.missingPqls));
            }
            else {
                this.handleSuccess();
            }
            if (Log.isLoggable("CachedModelProxy", 2)) {
                Log.v("CachedModelProxy", this.getClass().getSimpleName() + " run() time ms: " + (System.nanoTime() - nanoTime) / 1000000L);
            }
        }
    }
    
    private class FetchBillboardVideosTask extends CmpTask
    {
        private final int fromVideo;
        private final int toVideo;
        
        public FetchBillboardVideosTask(final int fromVideo, final int toVideo, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.fromVideo = fromVideo;
            this.toVideo = toVideo;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            buildBillboardPql(list, this.fromVideo, this.toVideo);
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onBBVideosFetched(Collections.emptyList(), status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onBBVideosFetched(CachedModelProxy.this.getVideos(getResult.pqls), CommonStatus.OK);
        }
    }
    
    private class FetchCwVideosTask extends CmpTask
    {
        private final int fromVideo;
        private final int toVideo;
        
        public FetchCwVideosTask(final int fromVideo, final int toVideo, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.fromVideo = fromVideo;
            this.toVideo = toVideo;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            buildCwPql(list, this.fromVideo, this.toVideo);
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onCWVideosFetched(Collections.emptyList(), status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onCWVideosFetched(CachedModelProxy.this.getVideos(getResult.pqls), CommonStatus.OK);
        }
    }
    
    private class FetchGenreListTask extends CmpTask
    {
        public FetchGenreListTask(final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            list.add(PQL.create("genreList"));
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onGenreListsFetched(Collections.emptyList(), status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onGenreListsFetched(CachedModelProxy.this.getGenreList(), CommonStatus.OK);
        }
    }
    
    private class FetchLoMosTask extends CmpTask
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
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onLoMosFetched(Collections.emptyList(), status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onLoMosFetched(CachedModelProxy.this.getLomos(getResult.pqls), CommonStatus.OK);
        }
    }
    
    private class FetchVideosTask extends CmpTask
    {
        private final int fromVideo;
        private final LoMo lomo;
        private final boolean shouldInjectSocialData;
        private final int toVideo;
        
        public FetchVideosTask(final LoMo lomo, final int fromVideo, final int toVideo, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.lomo = lomo;
            this.fromVideo = fromVideo;
            this.toVideo = toVideo;
            final NetflixService service = CachedModelProxy.this.serviceProvider.getService();
            this.shouldInjectSocialData = (fromVideo == 0 && service != null && LoMoUtils.shouldInjectSocialData(lomo, service.isCurrentProfileFacebookConnected()));
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            list.add(PQL.create("lists", this.lomo.getId(), PQL.range(this.fromVideo, this.toVideo), "summary"));
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onVideosFetched(Collections.emptyList(), status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            final List access$1000 = CachedModelProxy.this.getVideos(getResult.pqls);
            if (this.shouldInjectSocialData) {
                LoMoUtils.injectSocialData(this.lomo, access$1000);
            }
            browseAgentCallback.onVideosFetched(access$1000, CommonStatus.OK);
        }
    }
    
    public static class GetResult
    {
        public final List<PQL> foundPqls;
        public final List<PQL> ignoredPqls;
        public final List<PQL> missingPqls;
        public final Collection<PQL> pqls;
        
        public GetResult(final Collection<PQL> pqls) {
            this.pqls = pqls;
            this.missingPqls = new ArrayList<PQL>();
            this.foundPqls = new ArrayList<PQL>();
            this.ignoredPqls = new ArrayList<PQL>();
        }
        
        public boolean hasMissingPaths() {
            return this.missingPqls.size() > 0;
        }
        
        public void printPaths(final String s) {
            if (Log.isLoggable("CachedModelProxy", 2)) {
                final Iterator<PQL> iterator = this.foundPqls.iterator();
                while (iterator.hasNext()) {
                    Log.v(s, "Found PQL: " + iterator.next());
                }
                final Iterator<PQL> iterator2 = this.missingPqls.iterator();
                while (iterator2.hasNext()) {
                    Log.v(s, "Missing PQL: " + iterator2.next());
                }
                final Iterator<PQL> iterator3 = this.ignoredPqls.iterator();
                while (iterator3.hasNext()) {
                    Log.v(s, "Ignored PQL: " + iterator3.next());
                }
            }
        }
    }
    
    private class PrefetchGenreLoLoMoTask extends CmpTask
    {
        private final String genreId;
        private final boolean isKop;
        private final int toLomo;
        private final int toVideo;
        
        public PrefetchGenreLoLoMoTask(final String genreId, final int toLomo, final int toVideo, final boolean isKop, final BrowseAgentCallback browseAgentCallback) {
            super(browseAgentCallback);
            this.genreId = genreId;
            this.toLomo = toLomo;
            this.toVideo = toVideo;
            this.isKop = isKop;
        }
        
        @Override
        protected void buildPqlList(final List<PQL> list) {
            if (this.isKop) {
                list.add(PQL.create("flatGenre", this.genreId, PQL.range(this.toVideo), "summary"));
                return;
            }
            list.add(PQL.create("topGenre", this.genreId, PQL.range(this.toLomo), "summary"));
            list.add(PQL.create("topGenre", this.genreId, PQL.range(this.toLomo), PQL.range(this.toVideo), "summary"));
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onGenreLoLoMoPrefetched(status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onGenreLoLoMoPrefetched(CommonStatus.OK);
        }
    }
    
    private class PrefetchLoLoMoTask extends CmpTask
    {
        private final boolean includeExtraCharacters;
        private final int toBBVideo;
        private final int toCWVideo;
        private final int toLomo;
        private final int toVideo;
        
        public PrefetchLoLoMoTask(final int n, final int toLomo, final int toVideo, final int toCWVideo, final int toBBVideo, final boolean includeExtraCharacters, final BrowseAgentCallback browseAgentCallback) {
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
            buildCwPql(list, 0, this.toCWVideo);
            buildBillboardPql(list, 0, this.toBBVideo);
            if (this.includeExtraCharacters) {
                final int n = this.toVideo + 1;
                list.add(PQL.create("lolomo", "characters", PQL.range(n, this.toVideo + n), "summary"));
            }
        }
        
        @Override
        protected void callbackForFailure(final BrowseAgentCallback browseAgentCallback, final Status status) {
            browseAgentCallback.onLoLoMoPrefetched(status);
        }
        
        @Override
        protected void fetchResultsAndCallbackForSuccess(final BrowseAgentCallback browseAgentCallback, final GetResult getResult) {
            browseAgentCallback.onLoLoMoPrefetched(CommonStatus.OK);
        }
    }
}
