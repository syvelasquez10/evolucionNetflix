// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

public class voOSChunkInfoImpl implements voOSChunkInfo
{
    long mDuration;
    int mReserved1;
    int mReserved2;
    String mRootUrl;
    long mStartTime;
    long mTimeScale;
    int mType;
    String mUrl;
    
    public voOSChunkInfoImpl(final int mType, final String mRootUrl, final String mUrl, final long mStartTime, final long mDuration, final long mTimeScale, final int mReserved1, final int mReserved2) {
        this.mType = mType;
        this.mRootUrl = mRootUrl;
        this.mUrl = mUrl;
        this.mStartTime = mStartTime;
        this.mDuration = mDuration;
        this.mTimeScale = mTimeScale;
        this.mReserved1 = mReserved1;
        this.mReserved2 = mReserved2;
    }
    
    @Override
    public long Duration() {
        return this.mDuration;
    }
    
    @Override
    public int Reserved1() {
        return this.mReserved1;
    }
    
    @Override
    public int Reserved2() {
        return this.mReserved2;
    }
    
    @Override
    public String RootUrl() {
        return this.mRootUrl;
    }
    
    @Override
    public long StartTime() {
        return this.mStartTime;
    }
    
    @Override
    public long TimeScale() {
        return this.mTimeScale;
    }
    
    @Override
    public int Type() {
        return this.mType;
    }
    
    @Override
    public String Url() {
        return this.mUrl;
    }
}
