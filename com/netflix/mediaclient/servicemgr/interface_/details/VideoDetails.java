// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.servicemgr.interface_.FeatureEnabledProvider;

public interface VideoDetails extends FeatureEnabledProvider, Ratable, Video
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
    
    String getStoryUrl();
    
    String getSynopsis();
    
    String getTitleImgUrl();
    
    String getTvCardUrl();
    
    int getYear();
    
    boolean isAvailableToStream();
    
    boolean isInQueue();
}
