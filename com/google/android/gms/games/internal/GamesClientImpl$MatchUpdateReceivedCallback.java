// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$MatchUpdateReceivedCallback extends d$b<OnTurnBasedMatchUpdateReceivedListener>
{
    private final TurnBasedMatch WY;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$MatchUpdateReceivedCallback(final GamesClientImpl wr, final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener, final TurnBasedMatch wy) {
        this.Wr = wr;
        super(onTurnBasedMatchUpdateReceivedListener);
        this.WY = wy;
    }
    
    protected void b(final OnTurnBasedMatchUpdateReceivedListener onTurnBasedMatchUpdateReceivedListener) {
        onTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.WY);
    }
    
    @Override
    protected void gT() {
    }
}
