// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.search;

import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import java.util.List;

public interface SearchVideoListProvider
{
     <T extends SearchVideo> List<T> getVideosList();
    
    SearchTrackable getVideosListTrackable();
}
