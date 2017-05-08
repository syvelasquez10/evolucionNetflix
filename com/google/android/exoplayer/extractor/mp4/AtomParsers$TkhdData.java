// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

final class AtomParsers$TkhdData
{
    private final long duration;
    private final int id;
    private final int rotationDegrees;
    
    public AtomParsers$TkhdData(final int id, final long duration, final int rotationDegrees) {
        this.id = id;
        this.duration = duration;
        this.rotationDegrees = rotationDegrees;
    }
}
