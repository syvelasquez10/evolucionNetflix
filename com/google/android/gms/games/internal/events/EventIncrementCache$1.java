// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.events;

import java.util.Iterator;
import java.util.Map;
import android.os.Looper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.HashMap;
import android.os.Handler;

class EventIncrementCache$1 implements Runnable
{
    final /* synthetic */ EventIncrementCache aak;
    
    EventIncrementCache$1(final EventIncrementCache aak) {
        this.aak = aak;
    }
    
    @Override
    public void run() {
        this.aak.kN();
    }
}
