// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import java.util.List;

public interface SearchVideoList extends List<SearchVideo>
{
    SearchTrackable getVideosListTrackable();
}
