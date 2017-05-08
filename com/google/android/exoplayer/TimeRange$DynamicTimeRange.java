// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Clock;

public final class TimeRange$DynamicTimeRange implements TimeRange
{
    private final long bufferDepthUs;
    private final long elapsedRealtimeAtStartUs;
    private final long maxEndTimeUs;
    private final long minStartTimeUs;
    private final Clock systemClock;
    
    public TimeRange$DynamicTimeRange(final long minStartTimeUs, final long maxEndTimeUs, final long elapsedRealtimeAtStartUs, final long bufferDepthUs, final Clock systemClock) {
        this.minStartTimeUs = minStartTimeUs;
        this.maxEndTimeUs = maxEndTimeUs;
        this.elapsedRealtimeAtStartUs = elapsedRealtimeAtStartUs;
        this.bufferDepthUs = bufferDepthUs;
        this.systemClock = systemClock;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final TimeRange$DynamicTimeRange timeRange$DynamicTimeRange = (TimeRange$DynamicTimeRange)o;
            if (timeRange$DynamicTimeRange.minStartTimeUs != this.minStartTimeUs || timeRange$DynamicTimeRange.maxEndTimeUs != this.maxEndTimeUs || timeRange$DynamicTimeRange.elapsedRealtimeAtStartUs != this.elapsedRealtimeAtStartUs || timeRange$DynamicTimeRange.bufferDepthUs != this.bufferDepthUs) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public long[] getCurrentBoundsUs(final long[] array) {
        long[] array2 = null;
        Label_0018: {
            if (array != null) {
                array2 = array;
                if (array.length >= 2) {
                    break Label_0018;
                }
            }
            array2 = new long[2];
        }
        final long min = Math.min(this.maxEndTimeUs, this.systemClock.elapsedRealtime() * 1000L - this.elapsedRealtimeAtStartUs);
        long n = this.minStartTimeUs;
        if (this.bufferDepthUs != -1L) {
            n = Math.max(n, min - this.bufferDepthUs);
        }
        array2[0] = n;
        array2[1] = min;
        return array2;
    }
    
    @Override
    public int hashCode() {
        return ((((int)this.minStartTimeUs + 527) * 31 + (int)this.maxEndTimeUs) * 31 + (int)this.elapsedRealtimeAtStartUs) * 31 + (int)this.bufferDepthUs;
    }
}
