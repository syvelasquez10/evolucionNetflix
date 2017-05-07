// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.events;

import java.util.concurrent.atomic.AtomicReference;

public abstract class EventIncrementManager
{
    private final AtomicReference<EventIncrementCache> aal;
    
    public EventIncrementManager() {
        this.aal = new AtomicReference<EventIncrementCache>();
    }
    
    public void flush() {
        final EventIncrementCache eventIncrementCache = this.aal.get();
        if (eventIncrementCache != null) {
            eventIncrementCache.flush();
        }
    }
    
    protected abstract EventIncrementCache kv();
    
    public void n(final String s, final int n) {
        EventIncrementCache kv;
        if ((kv = this.aal.get()) == null && !this.aal.compareAndSet(null, kv = this.kv())) {
            kv = this.aal.get();
        }
        kv.w(s, n);
    }
}
