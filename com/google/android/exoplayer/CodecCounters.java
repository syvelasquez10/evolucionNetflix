// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

public final class CodecCounters
{
    public int codecInitCount;
    public int codecReleaseCount;
    public int droppedOutputBufferCount;
    public int inputBufferCount;
    public int maxConsecutiveDroppedOutputBufferCount;
    public int outputBuffersChangedCount;
    public int outputFormatChangedCount;
    public int renderedOutputBufferCount;
    public int skippedOutputBufferCount;
    
    public void ensureUpdated() {
    }
    // monitorenter(this)
    // monitorexit(this)
}
