// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideoListProvider;

public class SearchVideoList implements SearchVideoListProvider
{
    private SearchTrackableListSummary videoListSummary;
    private final List<SearchVideo> videos;
    
    public SearchVideoList() {
        this.videos = new ArrayList<SearchVideo>();
    }
    
    @Override
    public List<SearchVideo> getVideosList() {
        return this.videos;
    }
    
    @Override
    public SearchTrackable getVideosListTrackable() {
        return this.videoListSummary;
    }
    
    public void setVideoListTrackable(final SearchTrackableListSummary videoListSummary) {
        this.videoListSummary = videoListSummary;
    }
}
