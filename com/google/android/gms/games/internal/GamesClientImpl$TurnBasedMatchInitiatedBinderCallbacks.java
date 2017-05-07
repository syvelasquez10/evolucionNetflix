// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> XE;
    
    public GamesClientImpl$TurnBasedMatchInitiatedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult> baseImplementation$b) {
        this.Wr = wr;
        this.XE = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void q(final DataHolder dataHolder) {
        this.XE.b(new GamesClientImpl$InitiateMatchResultImpl(dataHolder));
    }
}
