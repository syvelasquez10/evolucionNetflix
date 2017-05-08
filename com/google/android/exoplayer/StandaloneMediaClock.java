// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import android.os.SystemClock;

final class StandaloneMediaClock implements MediaClock
{
    private long deltaUs;
    private long positionUs;
    private boolean started;
    
    private long elapsedRealtimeMinus(final long n) {
        return SystemClock.elapsedRealtime() * 1000L - n;
    }
    
    @Override
    public long getPositionUs() {
        if (this.started) {
            return this.elapsedRealtimeMinus(this.deltaUs);
        }
        return this.positionUs;
    }
    
    public void setPositionUs(final long positionUs) {
        this.positionUs = positionUs;
        this.deltaUs = this.elapsedRealtimeMinus(positionUs);
    }
    
    public void start() {
        if (!this.started) {
            this.started = true;
            this.deltaUs = this.elapsedRealtimeMinus(this.positionUs);
        }
    }
    
    public void stop() {
        if (this.started) {
            this.positionUs = this.elapsedRealtimeMinus(this.deltaUs);
            this.started = false;
        }
    }
}
