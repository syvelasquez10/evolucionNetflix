// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public interface SampleSource$SampleSourceReader
{
    boolean continueBuffering(final int p0, final long p1);
    
    void disable(final int p0);
    
    void enable(final int p0, final long p1);
    
    long getBufferedPositionUs();
    
    MediaFormat getFormat(final int p0);
    
    int getTrackCount();
    
    void maybeThrowError();
    
    boolean prepare(final long p0);
    
    int readData(final int p0, final long p1, final MediaFormatHolder p2, final SampleHolder p3);
    
    long readDiscontinuity(final int p0);
    
    void release();
    
    void seekToUs(final long p0);
}
