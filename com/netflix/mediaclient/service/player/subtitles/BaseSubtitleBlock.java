// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.Log;

public abstract class BaseSubtitleBlock implements SubtitleBlock
{
    protected static final String TAG = "nf_subtitles";
    protected long mEnd;
    protected String mId;
    protected float mMaxFontSizeMultiplier;
    protected long mStart;
    
    @Override
    public long getEnd() {
        return this.mEnd;
    }
    
    @Override
    public String getId() {
        return this.mId;
    }
    
    @Override
    public long getStart() {
        return this.mStart;
    }
    
    @Override
    public boolean isVisible(final long n) {
        return n >= this.mStart && n <= this.mEnd;
    }
    
    @Override
    public boolean isVisibleInGivenTimeRange(final long n, final long n2) {
        if (n > n2) {
            Log.e("nf_subtitles", "From can not be later than to!");
        }
        else if (n2 > this.mStart && n <= this.mEnd) {
            return true;
        }
        return false;
    }
}
