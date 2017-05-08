// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.common;

public class ImageDecodeOptionsBuilder
{
    private int mBackgroundColor;
    private boolean mDecodeAllFrames;
    private boolean mDecodePreviewFrame;
    private boolean mForceOldAnimationCode;
    private boolean mForceStaticImage;
    private int mMinDecodeIntervalMs;
    private boolean mUseLastFrameForPreview;
    
    public ImageDecodeOptionsBuilder() {
        this.mMinDecodeIntervalMs = 100;
        this.mBackgroundColor = -1;
    }
    
    public ImageDecodeOptions build() {
        return new ImageDecodeOptions(this);
    }
    
    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }
    
    public boolean getDecodeAllFrames() {
        return this.mDecodeAllFrames;
    }
    
    public boolean getDecodePreviewFrame() {
        return this.mDecodePreviewFrame;
    }
    
    public boolean getForceOldAnimationCode() {
        return this.mForceOldAnimationCode;
    }
    
    public boolean getForceStaticImage() {
        return this.mForceStaticImage;
    }
    
    public int getMinDecodeIntervalMs() {
        return this.mMinDecodeIntervalMs;
    }
    
    public boolean getUseLastFrameForPreview() {
        return this.mUseLastFrameForPreview;
    }
}
