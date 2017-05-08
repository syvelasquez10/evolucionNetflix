// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.cstats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.List;

public class ExponentialHistogram<Sample extends Number> implements Histogram<Sample>
{
    private List<Integer> buckets;
    private AtomicIntegerArray counts;
    private Sample maximum;
    private Sample minimum;
    private Integer numBuckets;
    private final Class<Sample> sampleLiteralType;
    
    public ExponentialHistogram(final Class<Sample> sampleLiteralType) {
        this.sampleLiteralType = sampleLiteralType;
    }
    
    public void addCount(final Sample sample) {
        this.addCount(sample, 1);
    }
    
    @Override
    public void addCount(final Sample sample, final int n) {
        final boolean b = true;
        int i = 0;
        this.assertStrongly(sample != null);
        this.assertStrongly(n >= 0);
        this.assertStrongly(this.buckets != null && this.counts != null && b);
        final int intValue = Integer.class.cast(sample);
        while (i < this.buckets.size()) {
            if (intValue <= this.buckets.get(i)) {
                this.counts.addAndGet(i, n);
                break;
            }
            ++i;
        }
    }
    
    void assertStrongly(final boolean b) {
        if (!b) {
            throw new AssertionError();
        }
    }
    
    public List<Integer> getBuckets() {
        return this.buckets;
    }
    
    public AtomicIntegerArray getCounts() {
        return this.counts;
    }
    
    public Map<String, Integer> getHistogramMapForJson() {
        int i = 0;
        this.assertStrongly(this.buckets.size() == this.counts.length());
        final HashMap<String, Integer> hashMap = new HashMap<String, Integer>(this.buckets.size());
        while (i < this.buckets.size()) {
            hashMap.put(this.buckets.get(i).toString(), this.counts.get(i));
            ++i;
        }
        return hashMap;
    }
    
    public String getLayoutForJson() {
        return String.format("%d/%d/%d", this.minimum, this.maximum, this.numBuckets);
    }
    
    public void initializeBucketRanges(Sample counts, final Sample maximum, final Integer numBuckets) {
        if (numBuckets < 1 || Integer.class.cast(counts) > Integer.class.cast(maximum)) {
            this.buckets = null;
            this.counts = null;
            this.numBuckets = 0;
            this.minimum = this.sampleLiteralType.cast(0);
            this.maximum = this.sampleLiteralType.cast(0);
            return;
        }
        if (this.buckets == null || this.buckets.size() != 0) {
            this.buckets = new ArrayList<Integer>(numBuckets);
            this.counts = null;
            this.minimum = counts;
            this.maximum = maximum;
            this.numBuckets = numBuckets;
        }
        final double log = Math.log(Integer.class.cast(maximum));
        this.buckets.add(0);
        this.buckets.add(Integer.class.cast(counts));
        int n = 1;
        while (true) {
            final int intValue = numBuckets;
            ++n;
            if (intValue <= n) {
                break;
            }
            final double log2 = Math.log(Integer.class.cast(counts));
            final Number n2 = this.sampleLiteralType.cast((int)Math.floor(Math.exp(log2 + (log - log2) / (numBuckets - n)) + 0.5));
            if (Integer.class.cast(n2) > Integer.class.cast(counts)) {
                counts = (Sample)n2;
            }
            else {
                counts = this.sampleLiteralType.cast(Integer.class.cast(counts) + 1);
            }
            this.buckets.add((int)Integer.class.cast(counts));
        }
        this.buckets.add(Integer.MAX_VALUE);
        this.counts = new AtomicIntegerArray(this.buckets.size());
        counts = (Sample)this.counts;
        // monitorenter(counts)
        int i = 0;
        try {
            while (i < this.buckets.size()) {
                this.counts.set(i, 0);
                ++i;
            }
        }
        finally {
        }
        // monitorexit(counts)
    }
    
    @Override
    public void reset() {
        this.assertStrongly(this.buckets.size() == this.counts.length());
        for (int i = 0; i < this.buckets.size(); ++i) {
            this.counts.set(i, 0);
        }
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        if (this.buckets == null || this.counts == null || this.buckets.size() != this.counts.length()) {
            sb.append("[]");
        }
        else {
            sb.append('[');
            for (int i = 0; i < this.buckets.size(); ++i) {
                if (i != 0) {
                    sb.append(" ");
                }
                sb.append(this.buckets.get(i)).append(':').append(this.counts.get(i));
            }
            sb.append(']');
        }
        return sb.toString();
    }
}
