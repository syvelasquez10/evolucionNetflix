// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer$ReliableMessageSentCallback;

final class GamesClientImpl$RealTimeReliableMessageBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    final RealTimeMultiplayer$ReliableMessageSentCallback Xo;
    
    public GamesClientImpl$RealTimeReliableMessageBinderCallbacks(final GamesClientImpl wr, final RealTimeMultiplayer$ReliableMessageSentCallback xo) {
        this.Wr = wr;
        this.Xo = xo;
    }
    
    @Override
    public void b(final int n, final int n2, final String s) {
        this.Wr.a(new GamesClientImpl$RealTimeMessageSentCallback(this.Wr, this.Xo, n, n2, s));
    }
}
