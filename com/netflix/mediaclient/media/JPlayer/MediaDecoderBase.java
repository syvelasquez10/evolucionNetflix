// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

public abstract class MediaDecoderBase
{
    static final int BUFFER_FLAG_CSD = 2;
    static final int BUFFER_FLAG_EOS = 4;
    static final int BUFFER_FLAG_SYNC = 1;
    static final int STATE_INIT = -1;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 1;
    static final int STATE_STOPPED = 0;
    protected boolean mAudioUseGetTimestampAPI;
    protected MediaDecoderBase$Clock mClock;
    protected MediaDecoderBase$EventListener mEventListener;
    protected MediaDecoderBase$Clock mRefClock;
    protected volatile int mState;
    
    public void enableAudioUseGetTimestampAPI() {
        this.mAudioUseGetTimestampAPI = true;
    }
    
    public abstract void flush();
    
    public MediaDecoderBase$Clock getClock() {
        if (this.mClock == null) {
            this.mClock = new MediaDecoderBase$Clock(this);
        }
        return this.mClock;
    }
    
    public boolean isDecoderCreated() {
        return this.mState == -1;
    }
    
    public boolean isPauseded() {
        return this.mState == 2;
    }
    
    public boolean isStopped() {
        return this.mState == 0;
    }
    
    public abstract void pause();
    
    public void removeEventListener() {
        this.mEventListener = null;
    }
    
    public abstract void restart();
    
    protected void setEventListener(final MediaDecoderBase$EventListener mEventListener) {
        this.mEventListener = mEventListener;
    }
    
    public void setReferenceClock(final MediaDecoderBase$Clock mRefClock) {
        this.mRefClock = mRefClock;
    }
    
    public abstract void start();
    
    public abstract void stop();
    
    public abstract void unpause();
}
