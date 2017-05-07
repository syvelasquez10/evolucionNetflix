// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

import java.util.List;

public interface VideoDetails extends Playable
{
    String getActors();
    
    String getBifUrl();
    
    String getCatalogIdUrl();
    
    String getCertification();
    
    String getCreators();
    
    List<FriendProfile> getFacebookFriends();
    
    String getGenres();
    
    String getHighResolutionLandscapeBoxArtUrl();
    
    String getHighResolutionPortraitBoxArtUrl();
    
    float getPredictedRating();
    
    String getQuality();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    String getTvCardUrl();
    
    float getUserRating();
    
    int getYear();
    
    boolean isInQueue();
    
    boolean isVideoHd();
    
    void setUserRating(final float p0);
}
