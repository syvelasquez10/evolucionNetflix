// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.Comparator;

final class SlidingPercentile$2 implements Comparator<SlidingPercentile$Sample>
{
    @Override
    public int compare(final SlidingPercentile$Sample slidingPercentile$Sample, final SlidingPercentile$Sample slidingPercentile$Sample2) {
        if (slidingPercentile$Sample.value < slidingPercentile$Sample2.value) {
            return -1;
        }
        if (slidingPercentile$Sample2.value < slidingPercentile$Sample.value) {
            return 1;
        }
        return 0;
    }
}
