// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model.details;

import com.netflix.mediaclient.servicemgr.model.Playable;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.Video;

public interface KidsCharacterDetails extends Video
{
    String getBifUrl();
    
    String getCatalogIdUrl();
    
    String getCharacterId();
    
    String getCharacterName();
    
    String getCharacterSynopsis();
    
    List<Video> getGallery();
    
    String getGalleryRequestId();
    
    int getGalleryTrackId();
    
    String getHighResolutionLandscapeBoxArtUrl();
    
    String getHighResolutionPortraitBoxArtUrl();
    
    Playable getPlayable();
    
    String getStoryUrl();
    
    String getSynopsis();
    
    String getWatchNextDispUrl();
}
