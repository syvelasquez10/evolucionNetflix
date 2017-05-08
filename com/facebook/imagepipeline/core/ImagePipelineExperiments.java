// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import com.facebook.common.webp.WebpSupportStatus;

public class ImagePipelineExperiments
{
    private boolean mDecodeFileDescriptorEnabled;
    private final int mForceSmallCacheThresholdBytes;
    private final boolean mWebpSupportEnabled;
    
    private ImagePipelineExperiments(final ImagePipelineExperiments$Builder imagePipelineExperiments$Builder, final ImagePipelineConfig$Builder imagePipelineConfig$Builder) {
        final boolean b = true;
        this.mForceSmallCacheThresholdBytes = imagePipelineExperiments$Builder.mForceSmallCacheThresholdBytes;
        this.mWebpSupportEnabled = (imagePipelineExperiments$Builder.mWebpSupportEnabled && WebpSupportStatus.sWebpLibraryPresent);
        this.mDecodeFileDescriptorEnabled = (imagePipelineConfig$Builder.isDownsampleEnabled() && imagePipelineExperiments$Builder.mDecodeFileDescriptorEnabled && b);
    }
    
    public int getForceSmallCacheThresholdBytes() {
        return this.mForceSmallCacheThresholdBytes;
    }
    
    public boolean isDecodeFileDescriptorEnabled() {
        return this.mDecodeFileDescriptorEnabled;
    }
    
    public boolean isWebpSupportEnabled() {
        return this.mWebpSupportEnabled;
    }
}
