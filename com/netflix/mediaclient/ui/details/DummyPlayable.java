// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.Playable;

public class DummyPlayable implements Playable
{
    @Override
    public boolean canBeSharedOnFacebook() {
        return true;
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
    public int getSeasonNumber() {
        return 0;
    }
    
    @Override
    public boolean isAutoPlayEnabled() {
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
}
