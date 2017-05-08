// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

public final class Allocation
{
    public final byte[] data;
    private final int offset;
    
    public Allocation(final byte[] data, final int offset) {
        this.data = data;
        this.offset = offset;
    }
    
    public int translateOffset(final int n) {
        return this.offset + n;
    }
}
