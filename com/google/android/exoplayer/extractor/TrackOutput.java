// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.MediaFormat;

public interface TrackOutput
{
    void format(final MediaFormat p0);
    
    int sampleData(final ExtractorInput p0, final int p1, final boolean p2);
    
    void sampleData(final ParsableByteArray p0, final int p1);
    
    void sampleMetadata(final long p0, final int p1, final int p2, final int p3, final byte[] p4);
}
