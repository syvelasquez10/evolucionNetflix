// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSVideoPerformanceImpl implements voOSVideoPerformance
{
    int mAverageDecodeTime;
    int mAverageRenderTime;
    int mCPULoad;
    int mCodecDropNum;
    int[] mCodecErrors;
    int mCodecErrorsNum;
    int mCodecTimeNum;
    int mDecodedNum;
    int mFrequency;
    int mJitterNum;
    int mLastTime;
    int mMaxFrequency;
    int mRenderDropNum;
    int mRenderNum;
    int mRenderTimeNum;
    int mSourceDropNum;
    int mSourceTimeNum;
    int mTotalCPULoad;
    int mWorstDecodeTime;
    int mWorstRenderTime;
    
    public voOSVideoPerformanceImpl() {
    }
    
    public voOSVideoPerformanceImpl(final int mLastTime, final int mSourceDropNum, final int mCodecDropNum, final int mRenderDropNum, final int mDecodedNum, final int mRenderNum, final int mSourceTimeNum, final int mCodecTimeNum, final int mRenderTimeNum, final int mJitterNum, final int mCodecErrorsNum, final int[] mCodecErrors, final int mcpuLoad, final int mFrequency, final int mMaxFrequency, final int mWorstDecodeTime, final int mWorstRenderTime, final int mAverageDecodeTime, final int mAverageRenderTime, final int mTotalCPULoad) {
        this.mLastTime = mLastTime;
        this.mSourceDropNum = mSourceDropNum;
        this.mCodecDropNum = mCodecDropNum;
        this.mRenderDropNum = mRenderDropNum;
        this.mDecodedNum = mDecodedNum;
        this.mRenderNum = mRenderNum;
        this.mSourceTimeNum = mSourceTimeNum;
        this.mCodecTimeNum = mCodecTimeNum;
        this.mRenderTimeNum = mRenderTimeNum;
        this.mJitterNum = mJitterNum;
        this.mCodecErrorsNum = mCodecErrorsNum;
        this.mCodecErrors = mCodecErrors;
        this.mCPULoad = mcpuLoad;
        this.mFrequency = mFrequency;
        this.mMaxFrequency = mMaxFrequency;
        this.mWorstDecodeTime = mWorstDecodeTime;
        this.mWorstRenderTime = mWorstRenderTime;
        this.mAverageDecodeTime = mAverageDecodeTime;
        this.mAverageRenderTime = mAverageRenderTime;
        this.mTotalCPULoad = mTotalCPULoad;
    }
    
    @Override
    public int AverageDecodeTime() {
        return this.mAverageDecodeTime;
    }
    
    @Override
    public int AverageRenderTime() {
        return this.mAverageRenderTime;
    }
    
    @Override
    public int CPULoad() {
        return this.mCPULoad;
    }
    
    @Override
    public int CodecDropNum() {
        return this.mCodecDropNum;
    }
    
    @Override
    public int[] CodecErrors() {
        return this.mCodecErrors;
    }
    
    @Override
    public int CodecErrorsNum() {
        return this.mCodecErrorsNum;
    }
    
    @Override
    public int CodecTimeNum() {
        return this.mCodecTimeNum;
    }
    
    @Override
    public int DecodedNum() {
        return this.mDecodedNum;
    }
    
    @Override
    public int Frequency() {
        return this.mFrequency;
    }
    
    @Override
    public int JitterNum() {
        return this.mJitterNum;
    }
    
    @Override
    public int LastTime() {
        return this.mLastTime;
    }
    
    @Override
    public int MaxFrequency() {
        return this.mMaxFrequency;
    }
    
    @Override
    public int RenderDropNum() {
        return this.mRenderDropNum;
    }
    
    @Override
    public int RenderNum() {
        return this.mRenderNum;
    }
    
    @Override
    public int RenderTimeNum() {
        return this.mRenderTimeNum;
    }
    
    @Override
    public int SourceDropNum() {
        return this.mSourceDropNum;
    }
    
    @Override
    public int SourceTimeNum() {
        return this.mSourceTimeNum;
    }
    
    @Override
    public int TotalCPULoad() {
        return this.mTotalCPULoad;
    }
    
    @Override
    public int WorstDecodeTime() {
        return this.mWorstDecodeTime;
    }
    
    @Override
    public int WorstRenderTime() {
        return this.mWorstRenderTime;
    }
    
    public void parser(final Parcel parcel) {
        this.mLastTime = parcel.readInt();
        this.mSourceDropNum = parcel.readInt();
        this.mCodecDropNum = parcel.readInt();
        this.mRenderDropNum = parcel.readInt();
        this.mDecodedNum = parcel.readInt();
        this.mRenderNum = parcel.readInt();
        this.mSourceTimeNum = parcel.readInt();
        this.mCodecTimeNum = parcel.readInt();
        this.mRenderTimeNum = parcel.readInt();
        this.mJitterNum = parcel.readInt();
        this.mCodecErrorsNum = parcel.readInt();
        this.mCodecErrors = new int[this.mCodecErrorsNum];
        for (int i = 0; i < this.mCodecErrorsNum; ++i) {
            this.mCodecErrors[i] = parcel.readInt();
        }
        this.mCPULoad = parcel.readInt();
        this.mFrequency = parcel.readInt();
        this.mMaxFrequency = parcel.readInt();
        this.mWorstDecodeTime = parcel.readInt();
        this.mWorstRenderTime = parcel.readInt();
        this.mAverageDecodeTime = parcel.readInt();
        this.mAverageRenderTime = parcel.readInt();
        this.mTotalCPULoad = parcel.readInt();
    }
}
