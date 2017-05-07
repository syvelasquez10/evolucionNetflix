// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.search;

import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import java.util.List;

public interface SearchVideoListProvider
{
     <T extends SearchVideo> List<T> getVideosList();
    
    SearchTrackable getVideosListTrackable();
}
