// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.cache;

import java.util.Set;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import java.util.Map;

public class HardBrowseCache implements HardCache
{
    private static final String TAG = "HardBrowseCache";
    private final Map<String, Object> cache;
    
    public HardBrowseCache() {
        this.cache = new HashMap<String, Object>();
    }
    
    @Override
    public void flush() {
        Log.v("HardBrowseCache", "Flushing cache");
        this.cache.clear();
    }
    
    @Override
    public Object get(final String s) {
        return this.cache.get(s);
    }
    
    @Override
    public Set<?> getKeySet() {
        return this.cache.keySet();
    }
    
    @Override
    public Object put(final String s, final Object o) {
        return this.cache.put(s, o);
    }
    
    @Override
    public Object remove(final String s) {
        return this.cache.remove(s);
    }
}
