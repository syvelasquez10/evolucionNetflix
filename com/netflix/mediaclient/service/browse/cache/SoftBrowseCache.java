// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.cache;

import java.util.Set;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.LoggingLruCache;

public class SoftBrowseCache implements SoftCache
{
    private static final String TAG = "SoftBrowseCache";
    private final LoggingLruCache<String, Object> cache;
    
    public SoftBrowseCache(final int n) {
        this.cache = new LoggingLruCache<String, Object>("SoftBrowseCache", n);
    }
    
    @Override
    public void flush() {
        Log.v("SoftBrowseCache", "- Flushing cache");
        this.cache.evictAll();
    }
    
    @Override
    public Object get(final String s) {
        return this.cache.get((Object)s);
    }
    
    @Override
    public Set<?> getKeySet() {
        return this.cache.snapshot().keySet();
    }
    
    @Override
    public Object put(final String s, final Object o) {
        return this.cache.put((Object)s, o);
    }
    
    @Override
    public Object remove(final String s) {
        return this.cache.remove((Object)s);
    }
}
