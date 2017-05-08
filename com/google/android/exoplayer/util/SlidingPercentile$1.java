// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.Comparator;

final class SlidingPercentile$1 implements Comparator<SlidingPercentile$Sample>
{
    @Override
    public int compare(final SlidingPercentile$Sample slidingPercentile$Sample, final SlidingPercentile$Sample slidingPercentile$Sample2) {
        return slidingPercentile$Sample.index - slidingPercentile$Sample2.index;
    }
}
