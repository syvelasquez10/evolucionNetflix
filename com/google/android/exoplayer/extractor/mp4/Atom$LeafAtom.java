// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.mp4;

import com.google.android.exoplayer.util.ParsableByteArray;

final class Atom$LeafAtom extends Atom
{
    public final ParsableByteArray data;
    
    public Atom$LeafAtom(final int n, final ParsableByteArray data) {
        super(n);
        this.data = data;
    }
}
