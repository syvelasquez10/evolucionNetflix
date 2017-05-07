// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

public class MediaDecoderBase$Clock
{
    private static final long UPDATE_PAHSE1_INTERVAL_MS = 20L;
    private static final long UPDATE_PAHSE1_SAMPLE_COUNT = 48000L;
    private static final long UPDATE_PAHSE2_INTERVAL_MS = 200L;
    private static final long UPDATE_PAHSE2_SAMPLE_COUNT = 240000L;
    private static final long UPDATE_PAHSE3_INTERVAL_MS = 2000L;
    private long mLastPts;
    private long mLastUpdateTime;
    private boolean mRunning;
    final /* synthetic */ MediaDecoderBase this$0;
    
    MediaDecoderBase$Clock(final MediaDecoderBase this$0) {
        this.this$0 = this$0;
        this.mLastPts = -1L;
        this.mLastUpdateTime = -1L;
        this.mRunning = false;
    }
    
    MediaDecoderBase$Clock(final MediaDecoderBase this$0, final long mLastPts) {
        this.this$0 = this$0;
        this.mLastPts = mLastPts;
        this.mLastUpdateTime = -1L;
        this.mRunning = false;
    }
    
    public void flush() {
        this.mLastPts = -1L;
        this.mLastUpdateTime = -1L;
    }
    
    public long get() {
        if (this.mLastPts < 0L) {
            return -1L;
        }
        if (this.mRunning && this.mLastUpdateTime >= 0L) {
            return System.currentTimeMillis() - this.mLastUpdateTime + this.mLastPts;
        }
        return this.mLastPts;
    }
    
    public long pause() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long mLastUpdateTime = this.mLastUpdateTime;
        if (this.mLastPts >= 0L) {
            this.mLastPts += currentTimeMillis - mLastUpdateTime;
        }
        this.mRunning = false;
        this.mLastUpdateTime = -1L;
        return this.mLastPts;
    }
    
    public boolean shouldUpdate(final long n) {
        final long n2 = System.currentTimeMillis() - this.mLastUpdateTime;
        if (n <= 48000L) {
            if (n2 < 20L) {
                return false;
            }
        }
        else if (n <= 240000L) {
            if (n2 < 200L) {
                return false;
            }
        }
        else if (n2 < 2000L) {
            return false;
        }
        return true;
    }
    
    public long unpause() {
        this.mRunning = true;
        return this.mLastPts;
    }
    
    public void update(final long mLastPts) {
        this.mLastPts = mLastPts;
        this.mLastUpdateTime = System.currentTimeMillis();
        this.unpause();
    }
    
    public void updateAndPause(final long mLastPts) {
        this.mLastPts = mLastPts;
        this.mLastUpdateTime = System.currentTimeMillis();
        this.mRunning = false;
    }
}
