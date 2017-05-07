// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class EventsImpl$3 extends EventsImpl$UpdateImpl
{
    final /* synthetic */ EventsImpl Ye;
    final /* synthetic */ String Yf;
    final /* synthetic */ int Yg;
    
    EventsImpl$3(final EventsImpl ye, final String yf, final int yg) {
        this.Ye = ye;
        this.Yf = yf;
        this.Yg = yg;
        super((EventsImpl$1)null);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.n(this.Yf, this.Yg);
    }
}
