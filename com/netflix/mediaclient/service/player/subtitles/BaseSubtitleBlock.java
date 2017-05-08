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
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Is block visible for position " + n + " when its start time " + this.mStart + " and its end time " + this.mEnd);
        }
        return n >= this.mStart && n <= this.mEnd;
    }
    
    @Override
    public boolean isVisibleInGivenTimeRange(final long n, final long n2) {
        final boolean b = false;
        final boolean b2 = false;
        if (Log.isLoggable()) {
            Log.d("nf_subtitles", "Is block will visible for range (" + n + ", " + n2 + ") when its start time " + this.mStart + " and its end time " + this.mEnd);
        }
        boolean b3;
        if (n > n2) {
            Log.e("nf_subtitles", "From can not be later than to!");
            b3 = b2;
        }
        else {
            boolean b4 = b;
            if (n2 > this.mStart) {
                b4 = b;
                if (n <= this.mEnd) {
                    b4 = true;
                }
            }
            b3 = b4;
            if (Log.isLoggable()) {
                Log.d("nf_subtitles", "Block is visible" + b4);
                return b4;
            }
        }
        return b3;
    }
    
    @Override
    public String toString() {
        return ", mId='" + this.mId + '\'' + ", mStart=" + this.mStart + ", mEnd=" + this.mEnd + ", mMaxFontSizeMultiplier=" + this.mMaxFontSizeMultiplier;
    }
}
