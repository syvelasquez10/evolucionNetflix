// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSPerformanceDataImpl implements voOSPerformanceData
{
    int mBitRate;
    int mCodecType;
    int mFPS;
    int mProfileLevel;
    int mVideoHeight;
    int mVideoWidth;
    
    public voOSPerformanceDataImpl(final int mCodecType, final int mBitRate, final int mVideoWidth, final int mVideoHeight, final int mProfileLevel, final int mfps) {
        this.mCodecType = mCodecType;
        this.mBitRate = mBitRate;
        this.mVideoWidth = mVideoWidth;
        this.mVideoHeight = mVideoHeight;
        this.mProfileLevel = mProfileLevel;
        this.mFPS = mfps;
    }
    
    @Override
    public int BitRate() {
        return this.mBitRate;
    }
    
    @Override
    public int CodecType() {
        return this.mCodecType;
    }
    
    @Override
    public int FPS() {
        return this.mFPS;
    }
    
    @Override
    public int ProfileLevel() {
        return this.mProfileLevel;
    }
    
    @Override
    public int VideoHeight() {
        return this.mVideoHeight;
    }
    
    @Override
    public int VideoWidth() {
        return this.mVideoWidth;
    }
}
