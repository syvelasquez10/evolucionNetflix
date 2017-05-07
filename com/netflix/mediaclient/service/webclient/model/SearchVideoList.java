// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.service.webclient.model.leafs.SearchTrackableListSummary;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import java.util.ArrayList;

public class SearchVideoList extends ArrayList<SearchVideo> implements com.netflix.mediaclient.servicemgr.model.SearchVideoList
{
    private static final long serialVersionUID = 174629524528102565L;
    private SearchTrackableListSummary videoListSummary;
    
    @Override
    public SearchTrackable getVideosListTrackable() {
        return this.videoListSummary;
    }
    
    public void setVideoListTrackable(final SearchTrackableListSummary videoListSummary) {
        this.videoListSummary = videoListSummary;
    }
}
