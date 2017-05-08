// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.MediaFormat;

final class AtomParsers$StsdData
{
    public MediaFormat mediaFormat;
    public int nalUnitLengthFieldLength;
    public final TrackEncryptionBox[] trackEncryptionBoxes;
    
    public AtomParsers$StsdData(final int n) {
        this.trackEncryptionBoxes = new TrackEncryptionBox[n];
        this.nalUnitLengthFieldLength = -1;
    }
}
