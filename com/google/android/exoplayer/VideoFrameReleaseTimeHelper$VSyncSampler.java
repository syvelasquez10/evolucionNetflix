// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.os.Message;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Choreographer;
import android.view.Choreographer$FrameCallback;
import android.os.Handler$Callback;

final class VideoFrameReleaseTimeHelper$VSyncSampler implements Handler$Callback, Choreographer$FrameCallback
{
    private static final VideoFrameReleaseTimeHelper$VSyncSampler INSTANCE;
    private Choreographer choreographer;
    private final HandlerThread choreographerOwnerThread;
    private final Handler handler;
    private int observerCount;
    public volatile long sampledVsyncTimeNs;
    
    static {
        INSTANCE = new VideoFrameReleaseTimeHelper$VSyncSampler();
    }
    
    private VideoFrameReleaseTimeHelper$VSyncSampler() {
        (this.choreographerOwnerThread = new HandlerThread("ChoreographerOwner:Handler")).start();
        (this.handler = new Handler(this.choreographerOwnerThread.getLooper(), (Handler$Callback)this)).sendEmptyMessage(0);
    }
    
    private void addObserverInternal() {
        ++this.observerCount;
        if (this.observerCount == 1) {
            this.choreographer.postFrameCallback((Choreographer$FrameCallback)this);
        }
    }
    
    private void createChoreographerInstanceInternal() {
        this.choreographer = Choreographer.getInstance();
    }
    
    public static VideoFrameReleaseTimeHelper$VSyncSampler getInstance() {
        return VideoFrameReleaseTimeHelper$VSyncSampler.INSTANCE;
    }
    
    private void removeObserverInternal() {
        --this.observerCount;
        if (this.observerCount == 0) {
            this.choreographer.removeFrameCallback((Choreographer$FrameCallback)this);
            this.sampledVsyncTimeNs = 0L;
        }
    }
    
    public void addObserver() {
        this.handler.sendEmptyMessage(1);
    }
    
    public void doFrame(final long sampledVsyncTimeNs) {
        this.sampledVsyncTimeNs = sampledVsyncTimeNs;
        this.choreographer.postFrameCallbackDelayed((Choreographer$FrameCallback)this, 500L);
    }
    
    public boolean handleMessage(final Message message) {
        switch (message.what) {
            default: {
                return false;
            }
            case 0: {
                this.createChoreographerInstanceInternal();
                return true;
            }
            case 1: {
                this.addObserverInternal();
                return true;
            }
            case 2: {
                this.removeObserverInternal();
                return true;
            }
        }
    }
    
    public void removeObserver() {
        this.handler.sendEmptyMessage(2);
    }
}
