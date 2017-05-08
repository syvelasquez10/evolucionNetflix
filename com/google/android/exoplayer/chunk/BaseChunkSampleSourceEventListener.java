// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import java.io.IOException;

public interface BaseChunkSampleSourceEventListener
{
    void onDownstreamFormatChanged(final int p0, final Format p1, final int p2, final long p3);
    
    void onLoadCanceled(final int p0, final long p1);
    
    void onLoadCompleted(final int p0, final long p1, final int p2, final int p3, final Format p4, final long p5, final long p6, final long p7, final long p8);
    
    void onLoadError(final int p0, final IOException p1);
    
    void onLoadStarted(final int p0, final long p1, final int p2, final int p3, final Format p4, final long p5, final long p6);
    
    void onUpstreamDiscarded(final int p0, final long p1, final long p2);
}
