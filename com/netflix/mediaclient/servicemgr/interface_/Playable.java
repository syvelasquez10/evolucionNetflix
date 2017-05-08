// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_;

import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;

public interface Playable
{
    List<Advisory> getAdvisories();
    
    int getAutoPlayMaxCount();
    
    int getEndtime();
    
    int getEpisodeNumber();
    
    long getExpirationTime();
    
    int getLogicalStart();
    
    String getParentId();
    
    String getParentTitle();
    
    int getPlayableBookmarkPosition();
    
    long getPlayableBookmarkUpdateTime();
    
    String getPlayableId();
    
    String getPlayableTitle();
    
    int getRuntime();
    
    String getSeasonAbbrSeqLabel();
    
    int getSeasonNumber();
    
    boolean isAdvisoryDisabled();
    
    boolean isAgeProtected();
    
    boolean isAutoPlayEnabled();
    
    boolean isAvailableToStream();
    
    boolean isExemptFromInterrupterLimit();
    
    boolean isNSRE();
    
    boolean isNextPlayableEpisode();
    
    boolean isPinProtected();
    
    boolean isPlayableEpisode();
    
    boolean isSupplementalVideo();
}
