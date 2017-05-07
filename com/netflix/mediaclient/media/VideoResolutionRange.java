// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

public final class VideoResolutionRange
{
    private int mMaxHeight;
    private int mMaxWidth;
    private int mMinHeight;
    private int mMinWidth;
    
    private VideoResolutionRange(final int mMaxWidth, final int mMaxHeight) {
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMaxWidth = mMaxWidth;
        this.mMaxHeight = mMaxHeight;
    }
    
    private VideoResolutionRange(final int mMinWidth, final int mMaxWidth, final int mMinHeight, final int mMaxHeight) {
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mMinWidth = mMinWidth;
        this.mMinHeight = mMinHeight;
        this.mMaxWidth = mMaxWidth;
        this.mMaxHeight = mMaxHeight;
    }
    
    public static VideoResolutionRange getVideoResolutionRangeFromMaxHieght(final int n) {
        if (n < 480) {
            return new VideoResolutionRange(512, 384);
        }
        if (n < 720) {
            return new VideoResolutionRange(720, 480);
        }
        if (n < 1080) {
            return new VideoResolutionRange(1280, 720);
        }
        if (n < 1440) {
            return new VideoResolutionRange(1920, 1080);
        }
        if (n < 2160) {
            return new VideoResolutionRange(2560, 1440);
        }
        return new VideoResolutionRange(3840, 2160);
    }
    
    public int getMaxHeight() {
        return this.mMaxHeight;
    }
    
    public int getMaxWidth() {
        return this.mMaxWidth;
    }
    
    public int getMinHeight() {
        return this.mMinHeight;
    }
    
    public int getMinWidth() {
        return this.mMinWidth;
    }
    
    @Override
    public String toString() {
        return "VideoResolutionRange {width = [ " + this.mMinWidth + ", " + this.mMaxWidth + " ], height = [ " + this.mMinHeight + ", " + this.mMaxHeight + " ] }";
    }
}
