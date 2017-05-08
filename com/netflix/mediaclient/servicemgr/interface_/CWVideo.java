// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface CWVideo extends ArtworkUrlProvider, CleanBoxart, Playable, Video
{
    String createModifiedBigStillUrl();
    
    String createModifiedSmallStillUrl();
    
    String createModifiedStillUrl();
    
    String getCurrentEpisodeTitle();
    
    String getInterestingSmallUrl();
    
    String getInterestingUrl();
    
    String getTrickplayBigImgBaseUrl();
    
    String getTrickplayImgBaseUrl();
}
