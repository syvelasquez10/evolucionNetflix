// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.extractor.TrackOutput;

public interface ChunkExtractorWrapper$SingleTrackOutput extends TrackOutput
{
    void drmInitData(final DrmInitData p0);
    
    void seekMap(final SeekMap p0);
}
