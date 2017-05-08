// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.SystemClock;
import com.google.android.exoplayer.util.SlidingPercentile;
import android.os.Handler;
import com.google.android.exoplayer.util.Clock;

class DefaultBandwidthMeter$1 implements Runnable
{
    final /* synthetic */ DefaultBandwidthMeter this$0;
    final /* synthetic */ long val$bitrate;
    final /* synthetic */ long val$bytes;
    final /* synthetic */ int val$elapsedMs;
    
    DefaultBandwidthMeter$1(final DefaultBandwidthMeter this$0, final int val$elapsedMs, final long val$bytes, final long val$bitrate) {
        this.this$0 = this$0;
        this.val$elapsedMs = val$elapsedMs;
        this.val$bytes = val$bytes;
        this.val$bitrate = val$bitrate;
    }
    
    @Override
    public void run() {
        this.this$0.eventListener.onBandwidthSample(this.val$elapsedMs, this.val$bytes, this.val$bitrate);
    }
}
