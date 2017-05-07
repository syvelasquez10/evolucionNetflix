// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.Ratable;
import com.netflix.mediaclient.servicemgr.model.HdEnabledProvider;

public interface VideoDetails extends HdEnabledProvider, Ratable, Video
{
    String getActors();
    
    String getBifUrl();
    
    String getCatalogIdUrl();
    
    String getCertification();
    
    String getGenres();
    
    String getHighResolutionLandscapeBoxArtUrl();
    
    String getHighResolutionPortraitBoxArtUrl();
    
    Playable getPlayable();
    
    String getQuality();
    
    String getStoryDispUrl();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    String getTitleImgUrl();
    
    String getTvCardUrl();
    
    int getYear();
    
    boolean isInQueue();
}
