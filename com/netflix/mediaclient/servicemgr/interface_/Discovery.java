// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

import com.netflix.model.branches.FalkorVideo;

public interface Discovery extends Playable, Video
{
    FalkorVideo getFalkorVideo();
    
    String getPivot1BoxartUrl();
    
    long getPivot1CollectionId();
    
    String getPivot1Title();
    
    int getPivot1TrackId();
    
    String getPivot2BoxartUrl();
    
    long getPivot2CollectionId();
    
    String getPivot2Title();
    
    int getPivot2TrackId();
    
    String getVertStoryArtUrl();
}
