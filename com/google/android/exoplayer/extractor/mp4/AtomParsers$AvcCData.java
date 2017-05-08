// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import java.util.List;

final class AtomParsers$AvcCData
{
    public final List<byte[]> initializationData;
    public final int nalUnitLengthFieldLength;
    public final float pixelWidthAspectRatio;
    
    public AtomParsers$AvcCData(final List<byte[]> initializationData, final int nalUnitLengthFieldLength, final float pixelWidthAspectRatio) {
        this.initializationData = initializationData;
        this.nalUnitLengthFieldLength = nalUnitLengthFieldLength;
        this.pixelWidthAspectRatio = pixelWidthAspectRatio;
    }
}
