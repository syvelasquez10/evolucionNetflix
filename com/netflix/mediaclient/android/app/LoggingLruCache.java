// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.Log;
import android.util.LruCache;

public class LoggingLruCache<K, V> extends LruCache<K, V>
{
    private final String tag;
    
    public LoggingLruCache(final String tag, final int n) {
        super(n);
        this.tag = tag;
    }
    
    protected void entryRemoved(final boolean b, final K k, final V v, final V v2) {
        final String tag = this.tag;
        final StringBuilder sb = new StringBuilder();
        String s;
        if (b) {
            s = "- Evicted: ";
        }
        else {
            s = "- Entry removed: ";
        }
        Log.v(tag, sb.append(s).append(k).toString());
        super.entryRemoved(b, (Object)k, (Object)v, (Object)v2);
    }
}
