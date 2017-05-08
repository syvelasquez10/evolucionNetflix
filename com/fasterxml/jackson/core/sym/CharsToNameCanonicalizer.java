// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.sym;

import java.util.Arrays;
import com.fasterxml.jackson.core.util.InternCache;
import com.fasterxml.jackson.core.JsonFactory$Feature;
import java.util.BitSet;

public final class CharsToNameCanonicalizer
{
    static final CharsToNameCanonicalizer sBootstrapSymbolTable;
    protected CharsToNameCanonicalizer$Bucket[] _buckets;
    protected boolean _canonicalize;
    protected boolean _dirty;
    protected final int _flags;
    private final int _hashSeed;
    protected int _indexMask;
    protected int _longestCollisionList;
    protected BitSet _overflows;
    protected CharsToNameCanonicalizer _parent;
    protected int _size;
    protected int _sizeThreshold;
    protected String[] _symbols;
    
    static {
        sBootstrapSymbolTable = new CharsToNameCanonicalizer();
    }
    
    private CharsToNameCanonicalizer() {
        this._canonicalize = true;
        this._flags = -1;
        this._dirty = true;
        this._hashSeed = 0;
        this._longestCollisionList = 0;
        this.initTables(64);
    }
    
    private CharsToNameCanonicalizer(final CharsToNameCanonicalizer parent, int length, final String[] symbols, final CharsToNameCanonicalizer$Bucket[] buckets, final int size, final int hashSeed, final int longestCollisionList) {
        this._parent = parent;
        this._flags = length;
        this._canonicalize = JsonFactory$Feature.CANONICALIZE_FIELD_NAMES.enabledIn(length);
        this._symbols = symbols;
        this._buckets = buckets;
        this._size = size;
        this._hashSeed = hashSeed;
        length = symbols.length;
        this._sizeThreshold = _thresholdSize(length);
        this._indexMask = length - 1;
        this._longestCollisionList = longestCollisionList;
        this._dirty = false;
    }
    
    private String _addSymbol(final char[] array, int n, int length, final int n2, int hashToIndex) {
        if (!this._dirty) {
            this.copyArrays();
            this._dirty = true;
        }
        else if (this._size >= this._sizeThreshold) {
            this.rehash();
            hashToIndex = this._hashToIndex(this.calcHash(array, n, length));
        }
        String intern;
        final String s = intern = new String(array, n, length);
        if (JsonFactory$Feature.INTERN_FIELD_NAMES.enabledIn(this._flags)) {
            intern = InternCache.instance.intern(s);
        }
        ++this._size;
        if (this._symbols[hashToIndex] == null) {
            return this._symbols[hashToIndex] = intern;
        }
        n = hashToIndex >> 1;
        final CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = new CharsToNameCanonicalizer$Bucket(intern, this._buckets[n]);
        length = charsToNameCanonicalizer$Bucket.length;
        if (length > 100) {
            this._handleSpillOverflow(n, charsToNameCanonicalizer$Bucket);
            return intern;
        }
        this._buckets[n] = charsToNameCanonicalizer$Bucket;
        this._longestCollisionList = Math.max(length, this._longestCollisionList);
        return intern;
    }
    
    private String _findSymbol2(final char[] array, final int n, final int n2, CharsToNameCanonicalizer$Bucket next) {
        while (next != null) {
            final String has = next.has(array, n, n2);
            if (has != null) {
                return has;
            }
            next = next.next;
        }
        return null;
    }
    
    private void _handleSpillOverflow(final int n, final CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket) {
        if (this._overflows == null) {
            (this._overflows = new BitSet()).set(n);
        }
        else if (this._overflows.get(n)) {
            if (JsonFactory$Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(this._flags)) {
                this.reportTooManyCollisions(100);
            }
            this._canonicalize = false;
        }
        else {
            this._overflows.set(n);
        }
        this._symbols[n + n] = charsToNameCanonicalizer$Bucket.symbol;
        this._buckets[n] = null;
        this._size -= charsToNameCanonicalizer$Bucket.length;
        this._longestCollisionList = -1;
    }
    
    private static int _thresholdSize(final int n) {
        return n - (n >> 2);
    }
    
    private void copyArrays() {
        final String[] symbols = this._symbols;
        this._symbols = Arrays.copyOf(symbols, symbols.length);
        final CharsToNameCanonicalizer$Bucket[] buckets = this._buckets;
        this._buckets = Arrays.copyOf(buckets, buckets.length);
    }
    
    public static CharsToNameCanonicalizer createRoot() {
        final long currentTimeMillis = System.currentTimeMillis();
        return createRoot((int)(currentTimeMillis >>> 32) + (int)currentTimeMillis | 0x1);
    }
    
    protected static CharsToNameCanonicalizer createRoot(final int n) {
        return CharsToNameCanonicalizer.sBootstrapSymbolTable.makeOrphan(n);
    }
    
    private void initTables(final int n) {
        this._symbols = new String[n];
        this._buckets = new CharsToNameCanonicalizer$Bucket[n >> 1];
        this._indexMask = n - 1;
        this._size = 0;
        this._longestCollisionList = 0;
        this._sizeThreshold = _thresholdSize(n);
    }
    
    private CharsToNameCanonicalizer makeOrphan(final int n) {
        return new CharsToNameCanonicalizer(null, -1, this._symbols, this._buckets, this._size, n, this._longestCollisionList);
    }
    
    private void mergeChild(final CharsToNameCanonicalizer charsToNameCanonicalizer) {
        if (charsToNameCanonicalizer.size() > 12000) {
            synchronized (this) {
                this.initTables(256);
                this._dirty = false;
                return;
            }
        }
        if (charsToNameCanonicalizer.size() > this.size()) {
            synchronized (this) {
                this._symbols = charsToNameCanonicalizer._symbols;
                this._buckets = charsToNameCanonicalizer._buckets;
                this._size = charsToNameCanonicalizer._size;
                this._sizeThreshold = charsToNameCanonicalizer._sizeThreshold;
                this._indexMask = charsToNameCanonicalizer._indexMask;
                this._longestCollisionList = charsToNameCanonicalizer._longestCollisionList;
                this._dirty = false;
            }
        }
    }
    
    private void rehash() {
        final int length = this._symbols.length;
        final int n = length + length;
        if (n > 65536) {
            this._size = 0;
            this._canonicalize = false;
            this._symbols = new String[64];
            this._buckets = new CharsToNameCanonicalizer$Bucket[32];
            this._indexMask = 63;
            this._dirty = true;
        }
        else {
            final String[] symbols = this._symbols;
            final CharsToNameCanonicalizer$Bucket[] buckets = this._buckets;
            this._symbols = new String[n];
            this._buckets = new CharsToNameCanonicalizer$Bucket[n >> 1];
            this._indexMask = n - 1;
            this._sizeThreshold = _thresholdSize(n);
            int i = 0;
            int max = 0;
            int n2 = 0;
            while (i < length) {
                final String s = symbols[i];
                int max2 = max;
                int n3 = n2;
                if (s != null) {
                    n3 = n2 + 1;
                    final int hashToIndex = this._hashToIndex(this.calcHash(s));
                    if (this._symbols[hashToIndex] == null) {
                        this._symbols[hashToIndex] = s;
                        max2 = max;
                    }
                    else {
                        final int n4 = hashToIndex >> 1;
                        final CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = new CharsToNameCanonicalizer$Bucket(s, this._buckets[n4]);
                        this._buckets[n4] = charsToNameCanonicalizer$Bucket;
                        max2 = Math.max(max, charsToNameCanonicalizer$Bucket.length);
                    }
                }
                ++i;
                max = max2;
                n2 = n3;
            }
            final int n5 = 0;
            int n6 = n2;
            for (int j = n5; j < length >> 1; ++j) {
                for (CharsToNameCanonicalizer$Bucket next = buckets[j]; next != null; next = next.next) {
                    ++n6;
                    final String symbol = next.symbol;
                    final int hashToIndex2 = this._hashToIndex(this.calcHash(symbol));
                    if (this._symbols[hashToIndex2] == null) {
                        this._symbols[hashToIndex2] = symbol;
                    }
                    else {
                        final int n7 = hashToIndex2 >> 1;
                        final CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket2 = new CharsToNameCanonicalizer$Bucket(symbol, this._buckets[n7]);
                        this._buckets[n7] = charsToNameCanonicalizer$Bucket2;
                        max = Math.max(max, charsToNameCanonicalizer$Bucket2.length);
                    }
                }
            }
            this._longestCollisionList = max;
            this._overflows = null;
            if (n6 != this._size) {
                throw new Error("Internal error on SymbolTable.rehash(): had " + this._size + " entries; now have " + n6 + ".");
            }
        }
    }
    
    public int _hashToIndex(int n) {
        n += n >>> 15;
        n ^= n << 7;
        return n + (n >>> 3) & this._indexMask;
    }
    
    public int calcHash(final String s) {
        final int length = s.length();
        int hashSeed = this._hashSeed;
        char char1;
        for (int i = 0; i < length; ++i, hashSeed = char1 + hashSeed * 33) {
            char1 = s.charAt(i);
        }
        int n;
        if ((n = hashSeed) == 0) {
            n = 1;
        }
        return n;
    }
    
    public int calcHash(final char[] array, int n, final int n2) {
        int hashSeed = this._hashSeed;
        for (int i = n; i < n + n2; ++i) {
            hashSeed = hashSeed * 33 + array[i];
        }
        if ((n = hashSeed) == 0) {
            n = 1;
        }
        return n;
    }
    
    public String findSymbol(final char[] array, final int n, final int n2, final int n3) {
        String s;
        if (n2 < 1) {
            s = "";
        }
        else {
            if (!this._canonicalize) {
                return new String(array, n, n2);
            }
            final int hashToIndex = this._hashToIndex(n3);
            final String s2 = this._symbols[hashToIndex];
            if (s2 != null) {
                if (s2.length() == n2) {
                    int n4 = 0;
                    while (s2.charAt(n4) == array[n + n4]) {
                        if (++n4 == n2) {
                            return s2;
                        }
                    }
                }
                final CharsToNameCanonicalizer$Bucket charsToNameCanonicalizer$Bucket = this._buckets[hashToIndex >> 1];
                if (charsToNameCanonicalizer$Bucket != null && ((s = charsToNameCanonicalizer$Bucket.has(array, n, n2)) != null || (s = this._findSymbol2(array, n, n2, charsToNameCanonicalizer$Bucket.next)) != null)) {
                    return s;
                }
            }
            return this._addSymbol(array, n, n2, n3, hashToIndex);
        }
        return s;
    }
    
    public int hashSeed() {
        return this._hashSeed;
    }
    
    public CharsToNameCanonicalizer makeChild(final int n) {
        synchronized (this) {
            final String[] symbols = this._symbols;
            final CharsToNameCanonicalizer$Bucket[] buckets = this._buckets;
            final int size = this._size;
            final int hashSeed = this._hashSeed;
            final int longestCollisionList = this._longestCollisionList;
            // monitorexit(this)
            return new CharsToNameCanonicalizer(this, n, symbols, buckets, size, hashSeed, longestCollisionList);
        }
    }
    
    public boolean maybeDirty() {
        return this._dirty;
    }
    
    public void release() {
        if (this.maybeDirty() && this._parent != null && this._canonicalize) {
            this._parent.mergeChild(this);
            this._dirty = false;
        }
    }
    
    protected void reportTooManyCollisions(final int n) {
        throw new IllegalStateException("Longest collision chain in symbol table (of size " + this._size + ") now exceeds maximum, " + n + " -- suspect a DoS attack based on hash collisions");
    }
    
    public int size() {
        return this._size;
    }
}
