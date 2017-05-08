// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.common;

import android.net.Uri;
import com.facebook.common.internal.Preconditions;

public class SimpleCacheKey implements CacheKey
{
    final String mKey;
    
    public SimpleCacheKey(final String s) {
        this.mKey = Preconditions.checkNotNull(s);
    }
    
    @Override
    public boolean containsUri(final Uri uri) {
        return this.mKey.contains(uri.toString());
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof SimpleCacheKey && this.mKey.equals(((SimpleCacheKey)o).mKey));
    }
    
    @Override
    public int hashCode() {
        return this.mKey.hashCode();
    }
    
    @Override
    public String toString() {
        return this.mKey;
    }
}
