// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.util.Arrays;

class LZWOutputStream$Key
{
    private final byte[] bytes;
    private final int hashCode;
    
    public LZWOutputStream$Key(final byte[] bytes) {
        this.bytes = bytes;
        this.hashCode = Arrays.hashCode(bytes);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof LZWOutputStream$Key && Arrays.equals(this.bytes, ((LZWOutputStream$Key)o).bytes));
    }
    
    @Override
    public int hashCode() {
        return this.hashCode;
    }
    
    @Override
    public String toString() {
        return Arrays.toString(this.bytes);
    }
}
