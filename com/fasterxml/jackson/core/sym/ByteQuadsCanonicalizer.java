// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.sym;

import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer
{
    protected int _count;
    protected final boolean _failOnDoS;
    protected int[] _hashArea;
    protected int _hashSize;
    protected boolean _intern;
    protected final ByteQuadsCanonicalizer _parent;
    protected int _secondaryStart;
    private final int _seed;
    protected int _spilloverEnd;
    protected final AtomicReference<ByteQuadsCanonicalizer$TableInfo> _tableInfo;
    protected int _tertiaryStart;
    
    private ByteQuadsCanonicalizer(final int n, final boolean intern, int i, final boolean failOnDoS) {
        final int n2 = 16;
        this._parent = null;
        this._seed = i;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        if (n < 16) {
            i = 16;
        }
        else {
            i = n;
            if ((n - 1 & n) != 0x0) {
                for (i = n2; i < n; i += i) {}
            }
        }
        this._tableInfo = new AtomicReference<ByteQuadsCanonicalizer$TableInfo>(ByteQuadsCanonicalizer$TableInfo.createInitial(i));
    }
    
    static int _calcTertiaryShift(int n) {
        n >>= 2;
        if (n < 64) {
            return 4;
        }
        if (n <= 256) {
            return 5;
        }
        if (n <= 1024) {
            return 6;
        }
        return 7;
    }
    
    private final int _spilloverStart() {
        final int hashSize = this._hashSize;
        return (hashSize << 3) - hashSize;
    }
    
    public static ByteQuadsCanonicalizer createRoot() {
        final long currentTimeMillis = System.currentTimeMillis();
        return createRoot((int)(currentTimeMillis >>> 32) + (int)currentTimeMillis | 0x1);
    }
    
    protected static ByteQuadsCanonicalizer createRoot(final int n) {
        return new ByteQuadsCanonicalizer(64, true, n, true);
    }
    
    public int primaryCount() {
        final int secondaryStart = this._secondaryStart;
        int n = 0;
        int n2;
        for (int i = 3; i < secondaryStart; i += 4, n = n2) {
            n2 = n;
            if (this._hashArea[i] != 0) {
                n2 = n + 1;
            }
        }
        return n;
    }
    
    public int secondaryCount() {
        final int secondaryStart = this._secondaryStart;
        final int tertiaryStart = this._tertiaryStart;
        int n = 0;
        int n2;
        for (int i = secondaryStart + 3; i < tertiaryStart; i += 4, n = n2) {
            n2 = n;
            if (this._hashArea[i] != 0) {
                n2 = n + 1;
            }
        }
        return n;
    }
    
    public int spilloverCount() {
        return this._spilloverEnd - this._spilloverStart() >> 2;
    }
    
    public int tertiaryCount() {
        final int n = this._tertiaryStart + 3;
        final int hashSize = this._hashSize;
        int n2 = 0;
        int n3;
        for (int i = n; i < hashSize + n; i += 4, n2 = n3) {
            n3 = n2;
            if (this._hashArea[i] != 0) {
                n3 = n2 + 1;
            }
        }
        return n2;
    }
    
    @Override
    public String toString() {
        final int primaryCount = this.primaryCount();
        final int secondaryCount = this.secondaryCount();
        final int tertiaryCount = this.tertiaryCount();
        final int spilloverCount = this.spilloverCount();
        final int totalCount = this.totalCount();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", this.getClass().getName(), this._count, this._hashSize, primaryCount, secondaryCount, tertiaryCount, spilloverCount, totalCount, primaryCount + secondaryCount + tertiaryCount + spilloverCount, totalCount);
    }
    
    public int totalCount() {
        final int hashSize = this._hashSize;
        int n = 0;
        int n2;
        for (int i = 3; i < hashSize << 3; i += 4, n = n2) {
            n2 = n;
            if (this._hashArea[i] != 0) {
                n2 = n + 1;
            }
        }
        return n;
    }
}
