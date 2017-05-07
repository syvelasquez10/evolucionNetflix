// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.VideoType;
import android.content.Context;

public interface SeasonDetails
{
    int getCurrentEpisodeNumber();
    
    String getId();
    
    int getNumOfEpisodes();
    
    int getSeasonNumber();
    
    String getSeasonNumberTitle(final Context p0);
    
    String getTitle();
    
    VideoType getType();
    
    int getYear();
}
