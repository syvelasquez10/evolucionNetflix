// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

public interface Extractor
{
    void init(final ExtractorOutput p0);
    
    int read(final ExtractorInput p0, final PositionHolder p1);
    
    void seek();
}
