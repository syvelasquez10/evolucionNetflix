// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media.JPlayer;

public class MediaDecoderPipe2$LocalStateNotifier
{
    private static final int STATE_FLUSHED = 5;
    private static final int STATE_FLUSHING = 4;
    private static final int STATE_PAUSED = 1;
    private static final int STATE_PAUSING = 2;
    private static final int STATE_PLAYING = 3;
    private static final int STATE_STOPPED = 7;
    private static final int STATE_STOPPING = 6;
    private int mState;
    final /* synthetic */ MediaDecoderPipe2 this$0;
    
    protected MediaDecoderPipe2$LocalStateNotifier(final MediaDecoderPipe2 this$0) {
        this.this$0 = this$0;
        this.mState = 1;
    }
    
    boolean isFlushed() {
        synchronized (this) {
            return this.mState == 5;
        }
    }
    
    boolean isFlushing() {
        synchronized (this) {
            return this.mState == 4;
        }
    }
    
    boolean isPaused() {
        boolean b = true;
        synchronized (this) {
            if (this.mState != 1) {
                b = false;
            }
            return b;
        }
    }
    
    boolean isPausing() {
        synchronized (this) {
            return this.mState == 2;
        }
    }
    
    boolean isPlaying() {
        synchronized (this) {
            return this.mState == 3;
        }
    }
    
    void onFlushed() {
        synchronized (this) {
            this.mState = 5;
        }
    }
    
    void onFlushing() {
        synchronized (this) {
            this.mState = 4;
        }
    }
    
    void onPaused() {
        synchronized (this) {
            this.mState = 1;
        }
    }
    
    void onPausing() {
        synchronized (this) {
            this.mState = 2;
        }
    }
    
    void onPlaying() {
        synchronized (this) {
            this.mState = 3;
        }
    }
    
    void onStopped() {
        synchronized (this) {
            this.mState = 7;
        }
    }
    
    void onStopping() {
        synchronized (this) {
            this.mState = 6;
        }
    }
}
