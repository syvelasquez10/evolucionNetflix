// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class EventsImpl$2 extends EventsImpl$LoadImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ EventsImpl Ye;
    
    EventsImpl$2(final EventsImpl ye, final boolean xu) {
        this.Ye = ye;
        this.XU = xu;
        super((EventsImpl$1)null);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.d((BaseImplementation$b<Events$LoadEventsResult>)this, this.XU);
    }
}
