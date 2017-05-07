// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

public class VideoWindowForPostplayWithAnimation$ScaleAnimationParameters
{
    private int mDurationInMS;
    private int mEndLeftMargin;
    private float mEndScale;
    private int mEndTopMargin;
    private int mStartLeftMargin;
    private float mStartScale;
    private int mStartTopMargin;
    
    public VideoWindowForPostplayWithAnimation$ScaleAnimationParameters(final int mDurationInMS, final int mStartLeftMargin, final int mStartTopMargin, final float mStartScale, final int mEndLeftMargin, final int mEndTopMargin, final float mEndScale) {
        this.mDurationInMS = mDurationInMS;
        this.mStartLeftMargin = mStartLeftMargin;
        this.mStartTopMargin = mStartTopMargin;
        this.mStartScale = mStartScale;
        this.mEndLeftMargin = mEndLeftMargin;
        this.mEndTopMargin = mEndTopMargin;
        this.mEndScale = mEndScale;
    }
    
    public int getDurationInMS() {
        return this.mDurationInMS;
    }
    
    public int getEndLeftMargin() {
        return this.mEndLeftMargin;
    }
    
    public float getEndScale() {
        return this.mEndScale;
    }
    
    public int getEndTopMargin() {
        return this.mEndTopMargin;
    }
    
    public int getStartLeftMargin() {
        return this.mStartLeftMargin;
    }
    
    public float getStartScale() {
        return this.mStartScale;
    }
    
    public int getStartTopMargin() {
        return this.mStartTopMargin;
    }
}
