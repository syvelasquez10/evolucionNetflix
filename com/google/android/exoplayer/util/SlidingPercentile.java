// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Comparator;

public final class SlidingPercentile
{
    private static final Comparator<SlidingPercentile$Sample> INDEX_COMPARATOR;
    private static final Comparator<SlidingPercentile$Sample> VALUE_COMPARATOR;
    private int currentSortOrder;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final SlidingPercentile$Sample[] recycledSamples;
    private final ArrayList<SlidingPercentile$Sample> samples;
    private int totalWeight;
    
    static {
        INDEX_COMPARATOR = new SlidingPercentile$1();
        VALUE_COMPARATOR = new SlidingPercentile$2();
    }
    
    public SlidingPercentile(final int maxWeight) {
        this.maxWeight = maxWeight;
        this.recycledSamples = new SlidingPercentile$Sample[5];
        this.samples = new ArrayList<SlidingPercentile$Sample>();
        this.currentSortOrder = -1;
    }
    
    private void ensureSortedByIndex() {
        if (this.currentSortOrder != 1) {
            Collections.sort(this.samples, SlidingPercentile.INDEX_COMPARATOR);
            this.currentSortOrder = 1;
        }
    }
    
    private void ensureSortedByValue() {
        if (this.currentSortOrder != 0) {
            Collections.sort(this.samples, SlidingPercentile.VALUE_COMPARATOR);
            this.currentSortOrder = 0;
        }
    }
    
    public void addSample(int weight, final float value) {
        this.ensureSortedByIndex();
        SlidingPercentile$Sample slidingPercentile$Sample;
        if (this.recycledSampleCount > 0) {
            final SlidingPercentile$Sample[] recycledSamples = this.recycledSamples;
            final int recycledSampleCount = this.recycledSampleCount - 1;
            this.recycledSampleCount = recycledSampleCount;
            slidingPercentile$Sample = recycledSamples[recycledSampleCount];
        }
        else {
            slidingPercentile$Sample = new SlidingPercentile$Sample(null);
        }
        slidingPercentile$Sample.index = this.nextSampleIndex++;
        slidingPercentile$Sample.weight = weight;
        slidingPercentile$Sample.value = value;
        this.samples.add(slidingPercentile$Sample);
        this.totalWeight += weight;
        while (this.totalWeight > this.maxWeight) {
            weight = this.totalWeight - this.maxWeight;
            final SlidingPercentile$Sample slidingPercentile$Sample2 = this.samples.get(0);
            if (slidingPercentile$Sample2.weight <= weight) {
                this.totalWeight -= slidingPercentile$Sample2.weight;
                this.samples.remove(0);
                if (this.recycledSampleCount >= 5) {
                    continue;
                }
                final SlidingPercentile$Sample[] recycledSamples2 = this.recycledSamples;
                weight = this.recycledSampleCount++;
                recycledSamples2[weight] = slidingPercentile$Sample2;
            }
            else {
                slidingPercentile$Sample2.weight -= weight;
                this.totalWeight -= weight;
            }
        }
    }
    
    public float getPercentile(final float n) {
        this.ensureSortedByValue();
        final float n2 = this.totalWeight;
        int i = 0;
        int n3 = 0;
        while (i < this.samples.size()) {
            final SlidingPercentile$Sample slidingPercentile$Sample = this.samples.get(i);
            n3 += slidingPercentile$Sample.weight;
            if (n3 >= n * n2) {
                return slidingPercentile$Sample.value;
            }
            ++i;
        }
        if (this.samples.isEmpty()) {
            return Float.NaN;
        }
        return this.samples.get(this.samples.size() - 1).value;
    }
}
