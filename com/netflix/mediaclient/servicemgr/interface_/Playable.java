// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

public interface Playable
{
    int getEndtime();
    
    int getEpisodeNumber();
    
    int getLogicalStart();
    
    String getParentId();
    
    String getParentTitle();
    
    int getPlayableBookmarkPosition();
    
    long getPlayableBookmarkUpdateTime();
    
    String getPlayableId();
    
    String getPlayableTitle();
    
    int getRuntime();
    
    int getSeasonNumber();
    
    boolean isAgeProtected();
    
    boolean isAutoPlayEnabled();
    
    boolean isNextPlayableEpisode();
    
    boolean isPinProtected();
    
    boolean isPlayableEpisode();
}
