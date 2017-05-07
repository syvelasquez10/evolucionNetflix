// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

public class MediaDecoderPipe$Clock
{
    private long mLastPts;
    private long mLastUpdateTime;
    private boolean mRunning;
    final /* synthetic */ MediaDecoderPipe this$0;
    
    MediaDecoderPipe$Clock(final MediaDecoderPipe this$0) {
        this.this$0 = this$0;
        this.mLastPts = -1L;
        this.mLastUpdateTime = -1L;
        this.mRunning = false;
    }
    
    MediaDecoderPipe$Clock(final MediaDecoderPipe this$0, final long mLastPts) {
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
    
    public long unpause() {
        this.mRunning = true;
        return this.mLastPts;
    }
    
    public void update(final long mLastPts) {
        this.mLastPts = mLastPts;
        this.mLastUpdateTime = System.currentTimeMillis();
        this.unpause();
    }
}
