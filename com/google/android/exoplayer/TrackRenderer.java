// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Assertions;

public abstract class TrackRenderer implements ExoPlayer$ExoPlayerComponent
{
    private int state;
    
    final void disable() {
        Assertions.checkState(this.state == 2);
        this.state = 1;
        this.onDisabled();
    }
    
    protected abstract boolean doPrepare(final long p0);
    
    protected abstract void doSomeWork(final long p0, final long p1);
    
    final void enable(final int n, final long n2, final boolean b) {
        boolean b2 = true;
        if (this.state != 1) {
            b2 = false;
        }
        Assertions.checkState(b2);
        this.state = 2;
        this.onEnabled(n, n2, b);
    }
    
    protected abstract long getBufferedPositionUs();
    
    protected abstract long getDurationUs();
    
    protected abstract MediaFormat getFormat(final int p0);
    
    protected MediaClock getMediaClock() {
        return null;
    }
    
    protected final int getState() {
        return this.state;
    }
    
    protected abstract int getTrackCount();
    
    @Override
    public void handleMessage(final int n, final Object o) {
    }
    
    protected abstract boolean isEnded();
    
    protected abstract boolean isReady();
    
    protected abstract void maybeThrowError();
    
    protected void onDisabled() {
    }
    
    protected void onEnabled(final int n, final long n2, final boolean b) {
    }
    
    protected void onReleased() {
    }
    
    protected void onStarted() {
    }
    
    protected void onStopped() {
    }
    
    final int prepare(final long n) {
        boolean state = true;
        Assertions.checkState(this.state == 0);
        if (!this.doPrepare(n)) {
            state = false;
        }
        return this.state = (state ? 1 : 0);
    }
    
    final void release() {
        Assertions.checkState(this.state != 2 && this.state != 3 && this.state != -1);
        this.state = -1;
        this.onReleased();
    }
    
    protected abstract void seekTo(final long p0);
    
    final void start() {
        Assertions.checkState(this.state == 2);
        this.state = 3;
        this.onStarted();
    }
    
    final void stop() {
        Assertions.checkState(this.state == 3);
        this.state = 2;
        this.onStopped();
    }
}
