// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.common;

import java.util.Locale;

public class ImageDecodeOptions
{
    private static final ImageDecodeOptions DEFAULTS;
    public final int backgroundColor;
    public final boolean decodeAllFrames;
    public final boolean decodePreviewFrame;
    public final boolean forceOldAnimationCode;
    public final boolean forceStaticImage;
    public final int minDecodeIntervalMs;
    public final boolean useLastFrameForPreview;
    
    static {
        DEFAULTS = newBuilder().build();
    }
    
    public ImageDecodeOptions(final ImageDecodeOptionsBuilder imageDecodeOptionsBuilder) {
        this.minDecodeIntervalMs = imageDecodeOptionsBuilder.getMinDecodeIntervalMs();
        this.backgroundColor = imageDecodeOptionsBuilder.getBackgroundColor();
        this.forceOldAnimationCode = imageDecodeOptionsBuilder.getForceOldAnimationCode();
        this.decodePreviewFrame = imageDecodeOptionsBuilder.getDecodePreviewFrame();
        this.useLastFrameForPreview = imageDecodeOptionsBuilder.getUseLastFrameForPreview();
        this.decodeAllFrames = imageDecodeOptionsBuilder.getDecodeAllFrames();
        this.forceStaticImage = imageDecodeOptionsBuilder.getForceStaticImage();
    }
    
    public static ImageDecodeOptions defaults() {
        return ImageDecodeOptions.DEFAULTS;
    }
    
    public static ImageDecodeOptionsBuilder newBuilder() {
        return new ImageDecodeOptionsBuilder();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final ImageDecodeOptions imageDecodeOptions = (ImageDecodeOptions)o;
            if (this.backgroundColor != imageDecodeOptions.backgroundColor) {
                return false;
            }
            if (this.forceOldAnimationCode != imageDecodeOptions.forceOldAnimationCode) {
                return false;
            }
            if (this.decodePreviewFrame != imageDecodeOptions.decodePreviewFrame) {
                return false;
            }
            if (this.useLastFrameForPreview != imageDecodeOptions.useLastFrameForPreview) {
                return false;
            }
            if (this.decodeAllFrames != imageDecodeOptions.decodeAllFrames) {
                return false;
            }
            if (this.forceStaticImage != imageDecodeOptions.forceStaticImage) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        final int backgroundColor = this.backgroundColor;
        int n;
        if (this.forceOldAnimationCode) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n + backgroundColor * 31;
    }
    
    @Override
    public String toString() {
        return String.format(null, "%d-%d-%b-%b-%b-%b-%b", this.minDecodeIntervalMs, this.backgroundColor, this.forceOldAnimationCode, this.decodePreviewFrame, this.useLastFrameForPreview, this.decodeAllFrames, this.forceStaticImage);
    }
}
