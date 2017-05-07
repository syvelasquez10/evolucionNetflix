// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.model.Playable;

class DummyMdxVideoDetails$1 implements Playable
{
    final /* synthetic */ DummyMdxVideoDetails this$0;
    
    DummyMdxVideoDetails$1(final DummyMdxVideoDetails this$0) {
        this.this$0 = this$0;
    }
    
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
    public int getLogicalStart() {
        return 0;
    }
    
    @Override
    public String getParentId() {
        return "70178217";
    }
    
    @Override
    public String getParentTitle() {
        return "Dummy parent title - extra long version of the string to test textView ellipsize functionality";
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
        return "70178217";
    }
    
    @Override
    public String getPlayableTitle() {
        return "Dummy playable title";
    }
    
    @Override
    public int getRuntime() {
        return 4980;
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
