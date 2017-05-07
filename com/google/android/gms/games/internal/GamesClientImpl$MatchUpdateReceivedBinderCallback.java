// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;

final class GamesClientImpl$MatchUpdateReceivedBinderCallback extends AbstractGamesCallbacks
{
    private final OnTurnBasedMatchUpdateReceivedListener WX;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$MatchUpdateReceivedBinderCallback(final GamesClientImpl wr, final OnTurnBasedMatchUpdateReceivedListener wx) {
        this.Wr = wr;
        this.WX = wx;
    }
    
    @Override
    public void onTurnBasedMatchRemoved(final String s) {
        this.Wr.a(new GamesClientImpl$MatchRemovedCallback(this.Wr, this.WX, s));
    }
    
    @Override
    public void t(final DataHolder dataHolder) {
        final TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
        TurnBasedMatch turnBasedMatch = null;
        try {
            if (turnBasedMatchBuffer.getCount() > 0) {
                turnBasedMatch = turnBasedMatchBuffer.get(0).freeze();
            }
            turnBasedMatchBuffer.release();
            if (turnBasedMatch != null) {
                this.Wr.a(new GamesClientImpl$MatchUpdateReceivedCallback(this.Wr, this.WX, turnBasedMatch));
            }
        }
        finally {
            turnBasedMatchBuffer.release();
        }
    }
}
