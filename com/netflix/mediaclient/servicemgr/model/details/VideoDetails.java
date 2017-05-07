// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.Playable;
import com.netflix.mediaclient.servicemgr.model.user.FriendProfile;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.Video;

public interface VideoDetails extends Video
{
    String getActors();
    
    String getBifUrl();
    
    String getCatalogIdUrl();
    
    String getCertification();
    
    List<FriendProfile> getFacebookFriends();
    
    String getGenres();
    
    String getHighResolutionLandscapeBoxArtUrl();
    
    String getHighResolutionPortraitBoxArtUrl();
    
    Playable getPlayable();
    
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
