// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;

interface EbmlReaderOutput
{
    void binaryElement(final int p0, final int p1, final ExtractorInput p2);
    
    void endMasterElement(final int p0);
    
    void floatElement(final int p0, final double p1);
    
    int getElementType(final int p0);
    
    void integerElement(final int p0, final long p1);
    
    boolean isLevel1Element(final int p0);
    
    void startMasterElement(final int p0, final long p1, final long p2);
    
    void stringElement(final int p0, final String p1);
}
