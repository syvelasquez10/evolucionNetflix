// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.util;

import java.util.concurrent.ConcurrentHashMap;

public final class InternCache extends ConcurrentHashMap<String, String>
{
    public static final InternCache instance;
    private final Object lock;
    
    static {
        instance = new InternCache();
    }
    
    private InternCache() {
        super(180, 0.8f, 4);
        this.lock = new Object();
    }
    
    public String intern(String intern) {
        final String s = ((ConcurrentHashMap<K, String>)this).get(intern);
        if (s != null) {
            return s;
        }
        Label_0048: {
            if (this.size() < 180) {
                break Label_0048;
            }
            synchronized (this.lock) {
                if (this.size() >= 180) {
                    this.clear();
                }
                // monitorexit(this.lock)
                intern = intern.intern();
                this.put(intern, intern);
                return intern;
            }
        }
    }
}
