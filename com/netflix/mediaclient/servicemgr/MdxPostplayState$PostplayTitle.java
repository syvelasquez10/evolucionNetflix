// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr;

public class MdxPostplayState$PostplayTitle
{
    private int mTitleId;
    private String mTitleType;
    final /* synthetic */ MdxPostplayState this$0;
    
    MdxPostplayState$PostplayTitle(final MdxPostplayState this$0, final int mTitleId, final String mTitleType) {
        this.this$0 = this$0;
        this.mTitleId = -1;
        this.mTitleId = mTitleId;
        this.mTitleType = mTitleType;
    }
    
    public int getId() {
        return this.mTitleId;
    }
    
    public boolean isEpisode() {
        return this.mTitleType != null && this.mTitleType.contains("episode");
    }
}
