// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.internal.events.EventIncrementCache;
import com.google.android.gms.games.internal.events.EventIncrementManager;

class GamesClientImpl$1 extends EventIncrementManager
{
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$1(final GamesClientImpl wr) {
        this.Wr = wr;
    }
    
    public EventIncrementCache kv() {
        return new GamesClientImpl$GameClientEventIncrementCache(this.Wr);
    }
}
