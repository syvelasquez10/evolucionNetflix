// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public final class TimeRange$StaticTimeRange implements TimeRange
{
    private final long endTimeUs;
    private final long startTimeUs;
    
    public TimeRange$StaticTimeRange(final long startTimeUs, final long endTimeUs) {
        this.startTimeUs = startTimeUs;
        this.endTimeUs = endTimeUs;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final TimeRange$StaticTimeRange timeRange$StaticTimeRange = (TimeRange$StaticTimeRange)o;
            if (timeRange$StaticTimeRange.startTimeUs != this.startTimeUs || timeRange$StaticTimeRange.endTimeUs != this.endTimeUs) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public long[] getCurrentBoundsUs(final long[] array) {
        long[] array2 = null;
        Label_0016: {
            if (array != null) {
                array2 = array;
                if (array.length >= 2) {
                    break Label_0016;
                }
            }
            array2 = new long[2];
        }
        array2[0] = this.startTimeUs;
        array2[1] = this.endTimeUs;
        return array2;
    }
    
    @Override
    public int hashCode() {
        return ((int)this.startTimeUs + 527) * 31 + (int)this.endTimeUs;
    }
}
