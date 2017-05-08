// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.DataSource;

public abstract class MediaChunk extends Chunk
{
    public final int chunkIndex;
    public final long endTimeUs;
    public final long startTimeUs;
    
    public MediaChunk(final DataSource dataSource, final DataSpec dataSpec, final int n, final Format format, final long startTimeUs, final long endTimeUs, final int chunkIndex, final int n2) {
        super(dataSource, dataSpec, 1, n, format, n2);
        Assertions.checkNotNull(format);
        this.startTimeUs = startTimeUs;
        this.endTimeUs = endTimeUs;
        this.chunkIndex = chunkIndex;
    }
    
    public int getNextChunkIndex() {
        return this.chunkIndex + 1;
    }
}
