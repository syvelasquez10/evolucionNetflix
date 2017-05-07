// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.SparseIntArray;

public abstract class GridLayoutManager$SpanSizeLookup
{
    private boolean mCacheSpanIndices;
    final SparseIntArray mSpanIndexCache;
    
    public GridLayoutManager$SpanSizeLookup() {
        this.mSpanIndexCache = new SparseIntArray();
        this.mCacheSpanIndices = false;
    }
    
    int findReferenceIndexFromCache(int n) {
        int i = 0;
        int n2 = this.mSpanIndexCache.size() - 1;
        while (i <= n2) {
            final int n3 = i + n2 >>> 1;
            if (this.mSpanIndexCache.keyAt(n3) < n) {
                i = n3 + 1;
            }
            else {
                n2 = n3 - 1;
            }
        }
        n = i - 1;
        if (n >= 0 && n < this.mSpanIndexCache.size()) {
            return this.mSpanIndexCache.keyAt(n);
        }
        return -1;
    }
    
    int getCachedSpanIndex(final int n, int spanIndex) {
        int n2;
        if (!this.mCacheSpanIndices) {
            n2 = this.getSpanIndex(n, spanIndex);
        }
        else if ((n2 = this.mSpanIndexCache.get(n, -1)) == -1) {
            spanIndex = this.getSpanIndex(n, spanIndex);
            this.mSpanIndexCache.put(n, spanIndex);
            return spanIndex;
        }
        return n2;
    }
    
    public int getSpanGroupIndex(int n, final int n2) {
        final int spanSize = this.getSpanSize(n);
        int i = 0;
        int n3 = 0;
        int n4 = 0;
        while (i < n) {
            final int spanSize2 = this.getSpanSize(i);
            final int n5 = n4 + spanSize2;
            int n6;
            int n7;
            if (n5 == n2) {
                n6 = n3 + 1;
                n7 = 0;
            }
            else if (n5 > n2) {
                n6 = n3 + 1;
                n7 = spanSize2;
            }
            else {
                final int n8 = n5;
                n6 = n3;
                n7 = n8;
            }
            final int n9 = i + 1;
            final int n10 = n7;
            n3 = n6;
            n4 = n10;
            i = n9;
        }
        n = n3;
        if (n4 + spanSize > n2) {
            n = n3 + 1;
        }
        return n;
    }
    
    public int getSpanIndex(final int n, final int n2) {
        final int spanSize = this.getSpanSize(n);
        if (spanSize != n2) {
            while (true) {
                Label_0129: {
                    if (!this.mCacheSpanIndices || this.mSpanIndexCache.size() <= 0) {
                        break Label_0129;
                    }
                    final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                    if (referenceIndexFromCache < 0) {
                        break Label_0129;
                    }
                    int n3 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                    int i = referenceIndexFromCache + 1;
                    while (i < n) {
                        final int spanSize2 = this.getSpanSize(i);
                        final int n4 = n3 + spanSize2;
                        if (n4 == n2) {
                            n3 = 0;
                        }
                        else {
                            n3 = spanSize2;
                            if (n4 <= n2) {
                                n3 = n4;
                            }
                        }
                        ++i;
                    }
                    if (n3 + spanSize <= n2) {
                        return n3;
                    }
                    return 0;
                }
                int i = 0;
                int n3 = 0;
                continue;
            }
        }
        return 0;
    }
    
    public abstract int getSpanSize(final int p0);
    
    public void invalidateSpanIndexCache() {
        this.mSpanIndexCache.clear();
    }
    
    public boolean isSpanIndexCacheEnabled() {
        return this.mCacheSpanIndices;
    }
    
    public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
        this.mCacheSpanIndices = mCacheSpanIndices;
    }
}
