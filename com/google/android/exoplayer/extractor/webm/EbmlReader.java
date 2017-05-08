// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;

interface EbmlReader
{
    void init(final EbmlReaderOutput p0);
    
    boolean read(final ExtractorInput p0);
    
    void reset();
}
