// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

public class ImagePipelineExperiments$Builder
{
    private final ImagePipelineConfig$Builder mConfigBuilder;
    private boolean mDecodeFileDescriptorEnabled;
    private int mForceSmallCacheThresholdBytes;
    private boolean mWebpSupportEnabled;
    
    public ImagePipelineExperiments$Builder(final ImagePipelineConfig$Builder mConfigBuilder) {
        this.mForceSmallCacheThresholdBytes = 0;
        this.mWebpSupportEnabled = false;
        this.mDecodeFileDescriptorEnabled = false;
        this.mConfigBuilder = mConfigBuilder;
    }
    
    public ImagePipelineExperiments build() {
        return new ImagePipelineExperiments(this, this.mConfigBuilder, null);
    }
}
