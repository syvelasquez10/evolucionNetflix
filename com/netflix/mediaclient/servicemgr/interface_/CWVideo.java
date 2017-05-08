// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface CWVideo extends Playable, Video
{
    String createModifiedBigStillUrl();
    
    String createModifiedStillUrl();
    
    String getCurrentEpisodeTitle();
    
    String getTrickplayBigImgBaseUrl();
    
    String getTrickplayImgBaseUrl();
}
