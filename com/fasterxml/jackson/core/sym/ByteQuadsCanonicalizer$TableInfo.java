// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.sym;

final class ByteQuadsCanonicalizer$TableInfo
{
    public final int count;
    public final int longNameOffset;
    public final int[] mainHash;
    public final String[] names;
    public final int size;
    public final int spilloverEnd;
    public final int tertiaryShift;
    
    public ByteQuadsCanonicalizer$TableInfo(final int size, final int count, final int tertiaryShift, final int[] mainHash, final String[] names, final int spilloverEnd, final int longNameOffset) {
        this.size = size;
        this.count = count;
        this.tertiaryShift = tertiaryShift;
        this.mainHash = mainHash;
        this.names = names;
        this.spilloverEnd = spilloverEnd;
        this.longNameOffset = longNameOffset;
    }
    
    public static ByteQuadsCanonicalizer$TableInfo createInitial(final int n) {
        final int n2 = n << 3;
        return new ByteQuadsCanonicalizer$TableInfo(n, 0, ByteQuadsCanonicalizer._calcTertiaryShift(n), new int[n2], new String[n << 1], n2 - n, n2);
    }
}
