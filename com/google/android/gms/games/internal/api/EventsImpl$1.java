// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class EventsImpl$1 extends EventsImpl$LoadImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String[] Yd;
    final /* synthetic */ EventsImpl Ye;
    
    EventsImpl$1(final EventsImpl ye, final boolean xu, final String[] yd) {
        this.Ye = ye;
        this.XU = xu;
        this.Yd = yd;
        super((EventsImpl$1)null);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Events$LoadEventsResult>)this, this.XU, this.Yd);
    }
}
