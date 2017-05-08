// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.SystemClock;
import com.google.android.exoplayer.util.SlidingPercentile;
import android.os.Handler;
import com.google.android.exoplayer.util.Clock;

public final class DefaultBandwidthMeter implements BandwidthMeter
{
    private long bitrateEstimate;
    private long bytesAccumulator;
    private final Clock clock;
    private final Handler eventHandler;
    private final BandwidthMeter$EventListener eventListener;
    private final SlidingPercentile slidingPercentile;
    private long startTimeMs;
    private int streamCount;
    
    public DefaultBandwidthMeter() {
        this(null, null);
    }
    
    public DefaultBandwidthMeter(final Handler handler, final BandwidthMeter$EventListener bandwidthMeter$EventListener) {
        this(handler, bandwidthMeter$EventListener, new SystemClock());
    }
    
    public DefaultBandwidthMeter(final Handler handler, final BandwidthMeter$EventListener bandwidthMeter$EventListener, final Clock clock) {
        this(handler, bandwidthMeter$EventListener, clock, 2000);
    }
    
    public DefaultBandwidthMeter(final Handler eventHandler, final BandwidthMeter$EventListener eventListener, final Clock clock, final int n) {
        this.eventHandler = eventHandler;
        this.eventListener = eventListener;
        this.clock = clock;
        this.slidingPercentile = new SlidingPercentile(n);
        this.bitrateEstimate = -1L;
    }
    
    private void notifyBandwidthSample(final int n, final long n2, final long n3) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new DefaultBandwidthMeter$1(this, n, n2, n3));
        }
    }
    
    @Override
    public long getBitrateEstimate() {
        synchronized (this) {
            return this.bitrateEstimate;
        }
    }
    
    @Override
    public void onBytesTransferred(final int n) {
        synchronized (this) {
            this.bytesAccumulator += n;
        }
    }
    
    @Override
    public void onTransferEnd() {
        synchronized (this) {
            Assertions.checkState(this.streamCount > 0);
            final long elapsedRealtime = this.clock.elapsedRealtime();
            final int n = (int)(elapsedRealtime - this.startTimeMs);
            if (n > 0) {
                this.slidingPercentile.addSample((int)Math.sqrt(this.bytesAccumulator), this.bytesAccumulator * 8000L / n);
                final float percentile = this.slidingPercentile.getPercentile(0.5f);
                long bitrateEstimate;
                if (Float.isNaN(percentile)) {
                    bitrateEstimate = -1L;
                }
                else {
                    bitrateEstimate = (long)percentile;
                }
                this.bitrateEstimate = bitrateEstimate;
                this.notifyBandwidthSample(n, this.bytesAccumulator, this.bitrateEstimate);
            }
            --this.streamCount;
            if (this.streamCount > 0) {
                this.startTimeMs = elapsedRealtime;
            }
            this.bytesAccumulator = 0L;
        }
    }
    
    @Override
    public void onTransferStart() {
        synchronized (this) {
            if (this.streamCount == 0) {
                this.startTimeMs = this.clock.elapsedRealtime();
            }
            ++this.streamCount;
        }
    }
}
