// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchCanceledBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$CancelMatchResult> XD;
    
    public GamesClientImpl$TurnBasedMatchCanceledBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$CancelMatchResult> baseImplementation$b) {
        this.Wr = wr;
        this.XD = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void h(final int n, final String s) {
        this.XD.b(new GamesClientImpl$CancelMatchResultImpl(new Status(n), s));
    }
}
