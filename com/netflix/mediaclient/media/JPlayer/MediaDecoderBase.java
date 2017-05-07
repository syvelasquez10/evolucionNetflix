// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

import java.nio.ByteBuffer;

public abstract class MediaDecoderBase
{
    static final int BUFFER_FLAG_CSD = 2;
    static final int BUFFER_FLAG_EOS = 4;
    static final int BUFFER_FLAG_SYNC = 1;
    static final int STATE_INIT = -1;
    static final int STATE_PAUSED = 2;
    static final int STATE_PLAYING = 1;
    static final int STATE_STOPPED = 0;
    protected Clock mClock;
    protected EventListener mEventListener;
    protected Clock mRefClock;
    protected volatile int mState;
    
    public abstract void flush();
    
    public Clock getClock() {
        if (this.mClock == null) {
            this.mClock = new Clock();
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
    
    public void setEventListener(final EventListener mEventListener) {
        this.mEventListener = mEventListener;
    }
    
    public void setReferenceClock(final Clock mRefClock) {
        this.mRefClock = mRefClock;
    }
    
    public abstract void start();
    
    public abstract void stop();
    
    public abstract void unpause();
    
    public class Clock
    {
        private static final long UPDATE_PAHSE1_INTERVAL_MS = 20L;
        private static final long UPDATE_PAHSE1_SAMPLE_COUNT = 48000L;
        private static final long UPDATE_PAHSE2_INTERVAL_MS = 200L;
        private static final long UPDATE_PAHSE2_SAMPLE_COUNT = 240000L;
        private static final long UPDATE_PAHSE3_INTERVAL_MS = 2000L;
        private long mLastPts;
        private long mLastUpdateTime;
        private boolean mRunning;
        
        Clock() {
            this.mLastPts = -1L;
            this.mLastUpdateTime = -1L;
            this.mRunning = false;
        }
        
        Clock(final long mLastPts) {
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
                return this.mLastPts + (System.currentTimeMillis() - this.mLastUpdateTime);
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
    }
    
    public interface EventListener
    {
        void onDecoderReady(final boolean p0);
        
        void onDecoderStarted(final boolean p0);
        
        void onEndOfStream(final boolean p0);
        
        void onFlushed(final boolean p0);
        
        void onPasued(final boolean p0);
        
        void onSampleRendered(final boolean p0, final long p1, final long p2);
    }
    
    public interface InputDataSource
    {
        BufferMeta onRequestData(final ByteBuffer p0);
        
        public static class BufferMeta
        {
            long debug;
            int flags;
            byte[] iv;
            byte[] key;
            int[] nByteEncrypted;
            int[] nByteInClear;
            int nSubsample;
            int offset;
            int size;
            long timestamp;
        }
    }
}
