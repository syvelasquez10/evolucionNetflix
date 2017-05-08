// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.model.leafs.advisory.Advisory;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.Playable;

public class DummyPlayable implements Playable
{
    @Override
    public List<Advisory> getAdvisories() {
        return null;
    }
    
    @Override
    public int getAutoPlayMaxCount() {
        return -1;
    }
    
    @Override
    public int getEndtime() {
        return 0;
    }
    
    @Override
    public int getEpisodeNumber() {
        return 0;
    }
    
    @Override
    public long getExpirationTime() {
        return 0L;
    }
    
    @Override
    public int getLogicalStart() {
        return 0;
    }
    
    @Override
    public String getParentId() {
        return null;
    }
    
    @Override
    public String getParentTitle() {
        return null;
    }
    
    @Override
    public int getPlayableBookmarkPosition() {
        return 0;
    }
    
    @Override
    public long getPlayableBookmarkUpdateTime() {
        return 0L;
    }
    
    @Override
    public String getPlayableId() {
        return null;
    }
    
    @Override
    public String getPlayableTitle() {
        return null;
    }
    
    @Override
    public int getRuntime() {
        return -1;
    }
    
    @Override
    public String getSeasonAbbrSeqLabel() {
        return null;
    }
    
    @Override
    public int getSeasonNumber() {
        return 0;
    }
    
    @Override
    public boolean isAdvisoryDisabled() {
        return false;
    }
    
    @Override
    public boolean isAgeProtected() {
        return false;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
        return false;
    }
    
    @Override
    public boolean isAvailableOffline() {
        return false;
    }
    
    @Override
    public boolean isAvailableToStream() {
        return false;
    }
    
    @Override
    public boolean isExemptFromInterrupterLimit() {
        return false;
    }
    
    @Override
    public boolean isNSRE() {
        return false;
    }
    
    @Override
    public boolean isNextPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isPinProtected() {
        return false;
    }
    
    @Override
    public boolean isPlayableEpisode() {
        return false;
    }
    
    @Override
    public boolean isSupplementalVideo() {
        return false;
    }
}
