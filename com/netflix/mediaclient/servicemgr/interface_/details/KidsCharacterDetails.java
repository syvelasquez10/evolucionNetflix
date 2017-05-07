// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.details;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.Video;

public interface KidsCharacterDetails extends Video
{
    String getBifUrl();
    
    String getCatalogIdUrl();
    
    String getCharacterId();
    
    String getCharacterName();
    
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
