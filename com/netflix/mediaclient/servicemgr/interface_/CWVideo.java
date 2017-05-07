// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface CWVideo extends Playable, Video
{
    String getBaseUrl();
    
    String getCurrentEpisodeTitle();
    
    String getInterestingUrl();
    
    String getModifiedStillUrl();
}
