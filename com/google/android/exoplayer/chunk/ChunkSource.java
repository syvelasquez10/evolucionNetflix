// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.MediaFormat;
import java.util.List;

public interface ChunkSource
{
    void continueBuffering(final long p0);
    
    void disable(final List<? extends MediaChunk> p0);
    
    void enable(final int p0);
    
    void getChunkOperation(final List<? extends MediaChunk> p0, final long p1, final ChunkOperationHolder p2);
    
    MediaFormat getFormat(final int p0);
    
    int getTrackCount();
    
    void maybeThrowError();
    
    void onChunkLoadCompleted(final Chunk p0);
    
    void onChunkLoadError(final Chunk p0, final Exception p1);
    
    boolean prepare();
}
