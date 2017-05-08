// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.dash;

import com.google.android.exoplayer.dash.mpd.RangedUri;
import com.google.android.exoplayer.extractor.ChunkIndex;

final class DashWrappingSegmentIndex implements DashSegmentIndex
{
    private final ChunkIndex chunkIndex;
    private final String uri;
    
    public DashWrappingSegmentIndex(final ChunkIndex chunkIndex, final String uri) {
        this.chunkIndex = chunkIndex;
        this.uri = uri;
    }
    
    @Override
    public long getDurationUs(final int n, final long n2) {
        return this.chunkIndex.durationsUs[n];
    }
    
    @Override
    public int getFirstSegmentNum() {
        return 0;
    }
    
    @Override
    public int getLastSegmentNum(final long n) {
        return this.chunkIndex.length - 1;
    }
    
    @Override
    public int getSegmentNum(final long n, final long n2) {
        return this.chunkIndex.getChunkIndex(n);
    }
    
    @Override
    public RangedUri getSegmentUrl(final int n) {
        return new RangedUri(this.uri, null, this.chunkIndex.offsets[n], this.chunkIndex.sizes[n]);
    }
    
    @Override
    public long getTimeUs(final int n) {
        return this.chunkIndex.timesUs[n];
    }
    
    @Override
    public boolean isExplicit() {
        return true;
    }
}
