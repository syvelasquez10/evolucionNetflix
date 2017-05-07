// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.model.branches;

import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideoListProvider;

public class SearchVideoListWrapper implements SearchVideoListProvider
{
    private final List<SearchVideo> videoList;
    private final SearchTrackable videoListSummary;
    
    public SearchVideoListWrapper(final List<SearchVideo> videoList, final SearchTrackable videoListSummary) {
        this.videoList = videoList;
        this.videoListSummary = videoListSummary;
    }
    
    @Override
    public List<SearchVideo> getVideosList() {
        return this.videoList;
    }
    
    @Override
    public SearchTrackable getVideosListTrackable() {
        return this.videoListSummary;
    }
}
