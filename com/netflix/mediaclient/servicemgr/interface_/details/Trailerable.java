// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;

public interface Trailerable
{
    List<Video> getTrailers();
    
    int getTrailersListPos();
    
    String getTrailersRequestId();
    
    int getTrailersTrackId();
}
