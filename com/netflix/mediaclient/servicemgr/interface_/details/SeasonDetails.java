// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.BasicVideo;

public interface SeasonDetails extends BasicVideo
{
    int getNumOfEpisodes();
    
    int getSeasonNumber();
    
    String getShowId();
    
    int getYear();
}
