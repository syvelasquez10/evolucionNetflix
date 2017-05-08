// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

public interface ExtractorInput
{
    long getPosition();
    
    void peekFully(final byte[] p0, final int p1, final int p2);
    
    int read(final byte[] p0, final int p1, final int p2);
    
    void readFully(final byte[] p0, final int p1, final int p2);
    
    boolean readFully(final byte[] p0, final int p1, final int p2, final boolean p3);
    
    void resetPeekPosition();
    
    void skipFully(final int p0);
}
