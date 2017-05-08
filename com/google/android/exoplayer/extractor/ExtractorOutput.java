// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.drm.DrmInitData;

public interface ExtractorOutput
{
    void drmInitData(final DrmInitData p0);
    
    void endTracks();
    
    void seekMap(final SeekMap p0);
    
    TrackOutput track(final int p0);
}
