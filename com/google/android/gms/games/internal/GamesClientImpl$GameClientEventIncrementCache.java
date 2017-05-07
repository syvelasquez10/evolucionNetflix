// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.games.internal.events.EventIncrementCache;

class GamesClientImpl$GameClientEventIncrementCache extends EventIncrementCache
{
    final /* synthetic */ GamesClientImpl Wr;
    
    public GamesClientImpl$GameClientEventIncrementCache(final GamesClientImpl wr) {
        this.Wr = wr;
        super(wr.getContext().getMainLooper(), 1000);
    }
    
    @Override
    protected void q(final String s, final int n) {
        try {
            if (this.Wr.isConnected()) {
                this.Wr.gS().n(s, n);
                return;
            }
            GamesLog.q("GamesClientImpl", "Unable to increment event " + s + " by " + n + " because the games client is no longer connected");
        }
        catch (RemoteException ex) {
            GamesLog.p("GamesClientImpl", "service died");
        }
    }
}
