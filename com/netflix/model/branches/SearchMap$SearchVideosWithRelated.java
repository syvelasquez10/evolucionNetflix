// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import java.util.Set;
import com.netflix.mediaclient.service.falkor.Falkor$Creator;
import com.netflix.falkor.BranchMap;
import com.netflix.model.leafs.SearchTrackableListSummary;
import com.netflix.falkor.Ref;

class SearchMap$SearchVideosWithRelated extends SummarizedList<Ref, SearchTrackableListSummary>
{
    private static final String RELATED_VIDEOS_KEY = "relatedVideos";
    private BranchMap<SummarizedList<Ref, SearchTrackableListSummary>> relatedSearchQueryMap;
    
    public SearchMap$SearchVideosWithRelated() {
        super(Falkor$Creator.Ref, Falkor$Creator.SearchTrackableListSummary);
    }
    
    @Override
    public Object get(final String s) {
        switch (s) {
            default: {
                return super.get(s);
            }
            case "relatedVideos": {
                return this.relatedSearchQueryMap;
            }
        }
    }
    
    @Override
    public Set<String> getKeys() {
        final Set<String> keys = super.getKeys();
        if (this.relatedSearchQueryMap != null) {
            keys.add("relatedVideos");
        }
        return keys;
    }
    
    @Override
    public Object getOrCreate(final String s) {
        final Object value = this.get(s);
        if (value != null) {
            return value;
        }
        switch (s) {
            default: {
                return super.getOrCreate(s);
            }
            case "relatedVideos": {
                return this.relatedSearchQueryMap = new BranchMap<SummarizedList<Ref, SearchTrackableListSummary>>(Falkor$Creator.SummarizedListOfSearchResults);
            }
        }
    }
    
    @Override
    public void set(final String s, final Object o) {
        if ("relatedVideos".equals(s)) {
            this.relatedSearchQueryMap = (BranchMap<SummarizedList<Ref, SearchTrackableListSummary>>)o;
            return;
        }
        super.set(s, o);
    }
}
