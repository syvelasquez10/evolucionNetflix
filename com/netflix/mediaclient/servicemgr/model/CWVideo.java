// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.model;

public interface CWVideo extends Playable, Video
{
    String getCurrentEpisodeTitle();
    
    String getInterestingUrl();
    
    String getStillUrl();
}
