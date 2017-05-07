// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$MatchRemovedCallback extends d$b<OnTurnBasedMatchUpdateReceivedListener>
{
    private final String WW;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$MatchRemovedCallback(final GamesClientImpl wr, final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final String ww) {
        this.Wr = wr;
        super(onTurnBasedMatchUpdateReceivedListener);
        this.WW = ww;
    }
    
    protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.WW);
    }
    
    @Override
    protected void gT() {
    }
}
