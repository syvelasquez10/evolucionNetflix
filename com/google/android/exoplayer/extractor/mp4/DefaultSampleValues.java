// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

final class DefaultSampleValues
{
    public final int duration;
    public final int flags;
    public final int sampleDescriptionIndex;
    public final int size;
    
    public DefaultSampleValues(final int sampleDescriptionIndex, final int duration, final int size, final int flags) {
        this.sampleDescriptionIndex = sampleDescriptionIndex;
        this.duration = duration;
        this.size = size;
        this.flags = flags;
    }
}
