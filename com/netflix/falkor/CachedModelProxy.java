// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import java.util.Set;
import java.util.Collection;
import java.util.Collections;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.FalkorVideo;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.model.SummarizedList;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.netflix.mediaclient.NetflixApplication;
import com.google.gson.JsonParser;
import com.google.gson.Gson;

public class CachedModelProxy<T extends BranchNode> implements ModelProxy<T>
{
    private static final String LISTS = "lists";
    private static final String LOLOMO = "lolomo";
    private static final String LOLOMOS = "lolomos";
    private static final String MOVIES = "movies";
    private static final String SHOWS = "shows";
    private static final String SUMMARY = "summary";
    private static final String TAG = "CachedModelProxy";
    private static final String VALUE = "value";
    private final Gson gson;
    private final JsonParser jsonParser;
    private final T root;
    
    public CachedModelProxy(final RequestProvider requestProvider, final T root) {
        this.gson = NetflixApplication.getGson();
        this.jsonParser = new JsonParser();
        this.root = root;
    }
    
    private void get(final PQL pql, final Object o, final int n, final GetResult getResult) {
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
    
    private void handleRefEntry(final BranchMap<?> branchMap, final Map.Entry<String, JsonElement> entry) {
        final String s = entry.getKey();
        if (entry.getValue().isJsonObject() && entry.getValue().getAsJsonObject().has("_sentinel")) {
            branchMap.set(s, new Undefined());
            return;
        }
        ((Ref)branchMap.getOrCreate(s)).setRefPath(PQL.fromJsonArray(entry.getValue().getAsJsonArray()));
    }
    
    private void mergeLists(final JsonObject jsonObject) {
        final BranchMap branchMap = (BranchMap)this.root.getOrCreate("lists");
        int listPos = 0;
        for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            final SummarizedList list = (SummarizedList)branchMap.getOrCreate(entry.getKey());
            for (final Map.Entry<Object, V> entry2 : entry.getValue().getAsJsonObject().entrySet()) {
                if ("summary".equals(entry2.getKey())) {
                    final ListOfMoviesSummary summary = this.gson.fromJson(entry2.getValue(), ListOfMoviesSummary.class);
                    summary.setListPos(listPos);
                    list.setSummary(summary);
                }
                else {
                    this.handleRefEntry(list, (Map.Entry<String, JsonElement>)entry2);
                }
            }
            ++listPos;
        }
    }
    
    private void mergeLolomo(final JsonArray jsonArray) {
        ((Ref)this.root.getOrCreate("lolomo")).setRefPath(PQL.fromJsonArray(jsonArray));
    }
    
    private void mergeLolomos(final JsonObject jsonObject) {
        final BranchMap branchMap = (BranchMap)this.root.getOrCreate("lolomos");
        for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            final SummarizedList list = (SummarizedList)branchMap.getOrCreate(entry.getKey());
            final Iterator<Map.Entry<String, JsonElement>> iterator2 = entry.getValue().getAsJsonObject().entrySet().iterator();
            while (iterator2.hasNext()) {
                this.handleRefEntry(list, (Map.Entry)iterator2.next());
            }
        }
    }
    
    private void mergeVideos(final JsonObject jsonObject, final String s) {
        final BranchMap branchMap = (BranchMap)this.root.getOrCreate(s);
        for (final Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            final FalkorVideo falkorVideo = (FalkorVideo)branchMap.getOrCreate(entry.getKey());
            for (final Map.Entry<String, JsonElement> entry2 : entry.getValue().getAsJsonObject().entrySet()) {
                falkorVideo.set(entry2.getKey(), FalcorParseUtils.createObjectFromJsonEntry(entry2));
            }
        }
    }
    
    public GetResult get(final PQL pql) {
        synchronized (this) {
            return this.get(Collections.singleton(pql));
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
    
    public Set<String> getCacheKeys() {
        synchronized (this) {
            return this.root.getKeys();
        }
    }
    
    public List<LoMo> getLomos(final Collection<PQL> collection) {
        final ArrayList<LoMo> list = new ArrayList<LoMo>(20);
        final Iterator<PQL> iterator = collection.iterator();
        while (iterator.hasNext()) {
            for (final PQL pql : iterator.next().explode()) {
                final Object value = this.getValue(pql);
                if (value instanceof LoMo) {
                    final LoMo loMo = (LoMo)value;
                    Log.v("CachedModelProxy", "got lomo - pql: " + pql + ", lomo: " + loMo.getTitle() + ", order: " + loMo.getListPos());
                    list.add(loMo);
                }
            }
        }
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Lomos size: " + list.size());
        }
        return list;
    }
    
    @Override
    public Object getValue(final PQL pql) {
        while (true) {
            while (true) {
                int n = 0;
                Label_0178: {
                    synchronized (this) {
                        Object root;
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
                                    break Label_0178;
                                }
                                Object o2;
                                final Object o = o2 = branchNode.get(value);
                                if (o instanceof Ref) {
                                    root = o;
                                    if (n == size - 1) {
                                        return root;
                                    }
                                    o2 = ((Ref)root).getValue(this);
                                }
                                if (o2 instanceof BranchNode) {
                                    branchNode = (BranchNode)o2;
                                    break Label_0178;
                                }
                                root = o2;
                                if (!(o2 instanceof Exception)) {
                                    final boolean b = o2 instanceof Undefined;
                                    root = o2;
                                    if (b) {
                                        root = o2;
                                    }
                                }
                            }
                        }
                        return root;
                    }
                }
                ++n;
                continue;
            }
        }
    }
    
    public List<Video> getVideos(final Collection<PQL> collection) {
        final ArrayList<Video> list = new ArrayList<Video>();
        final Iterator<PQL> iterator = collection.iterator();
        while (iterator.hasNext()) {
            for (final PQL pql : iterator.next().explode()) {
                final Object value = this.getValue(pql);
                if (value instanceof Video) {
                    final Video video = (Video)value;
                    Log.v("CachedModelProxy", "got video - pql: " + pql + ", video: " + video.getTitle());
                    list.add(video);
                }
            }
        }
        if (Log.isLoggable("CachedModelProxy", 2)) {
            Log.v("CachedModelProxy", "Videos size: " + list.size());
        }
        return list;
    }
    
    public void merge(final String s) {
        synchronized (this) {
            final JsonObject asJsonObject = this.jsonParser.parse(s).getAsJsonObject().getAsJsonObject("value");
            if (asJsonObject.has("lolomo")) {
                this.mergeLolomo(asJsonObject.getAsJsonArray("lolomo"));
            }
            if (asJsonObject.has("lolomos")) {
                this.mergeLolomos(asJsonObject.getAsJsonObject("lolomos"));
            }
            if (asJsonObject.has("lists")) {
                this.mergeLists(asJsonObject.getAsJsonObject("lists"));
            }
            if (asJsonObject.has("shows")) {
                this.mergeVideos(asJsonObject.getAsJsonObject("shows"), "shows");
            }
            if (asJsonObject.has("movies")) {
                this.mergeVideos(asJsonObject.getAsJsonObject("movies"), "movies");
            }
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
}
