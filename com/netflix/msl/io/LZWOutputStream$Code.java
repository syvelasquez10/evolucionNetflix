// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

class LZWOutputStream$Code
{
    public final int bits;
    public final int value;
    
    public LZWOutputStream$Code(final int value, final int bits) {
        this.value = value;
        this.bits = bits;
    }
    
    @Override
    public String toString() {
        return Integer.toHexString(this.value) + " (" + this.bits + "b)";
    }
}
