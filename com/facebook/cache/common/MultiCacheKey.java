// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

import android.net.Uri;
import java.util.List;

public class MultiCacheKey implements CacheKey
{
    final List<CacheKey> mCacheKeys;
    
    @Override
    public boolean containsUri(final Uri uri) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= this.mCacheKeys.size()) {
                break;
            }
            if (this.mCacheKeys.get(n).containsUri(uri)) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof MultiCacheKey && this.mCacheKeys.equals(((MultiCacheKey)o).mCacheKeys));
    }
    
    public List<CacheKey> getCacheKeys() {
        return this.mCacheKeys;
    }
    
    @Override
    public int hashCode() {
        return this.mCacheKeys.hashCode();
    }
    
    @Override
    public String toString() {
        return "MultiCacheKey:" + this.mCacheKeys.toString();
    }
}
