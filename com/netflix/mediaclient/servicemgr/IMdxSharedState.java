// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public interface IMdxSharedState
{
    MdxPlaybackState getMdxPlaybackState();
    
    long getPlaybackPositionInMs();
    
    String getPostplayState();
    
    int getRecentVolume();
    
    boolean hasActivePlayback();
    
    boolean isVolumeEnabled();
    
    public enum MdxPlaybackState
    {
        Loading, 
        Paused, 
        Playing, 
        Stopped, 
        Transitioning;
    }
}
