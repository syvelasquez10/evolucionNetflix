// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.util.Util;

public final class ChunkIndex implements SeekMap
{
    public final long[] durationsUs;
    public final int length;
    public final long[] offsets;
    public final int[] sizes;
    public final long[] timesUs;
    
    public ChunkIndex(final int[] sizes, final long[] offsets, final long[] durationsUs, final long[] timesUs) {
        this.length = sizes.length;
        this.sizes = sizes;
        this.offsets = offsets;
        this.durationsUs = durationsUs;
        this.timesUs = timesUs;
    }
    
    public int getChunkIndex(final long n) {
        return Util.binarySearchFloor(this.timesUs, n, true, true);
    }
    
    public boolean isSeekable() {
        return true;
    }
}
