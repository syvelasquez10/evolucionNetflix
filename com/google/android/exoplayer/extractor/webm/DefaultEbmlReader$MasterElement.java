// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

final class DefaultEbmlReader$MasterElement
{
    private final long elementEndPosition;
    private final int elementId;
    
    private DefaultEbmlReader$MasterElement(final int elementId, final long elementEndPosition) {
        this.elementId = elementId;
        this.elementEndPosition = elementEndPosition;
    }
}
