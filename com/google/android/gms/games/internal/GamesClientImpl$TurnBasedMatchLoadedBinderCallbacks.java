// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchResult> XG;
    
    public GamesClientImpl$TurnBasedMatchLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchResult> baseImplementation$b) {
        this.Wr = wr;
        this.XG = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void p(final DataHolder dataHolder) {
        this.XG.b(new GamesClientImpl$LoadMatchResultImpl(dataHolder));
    }
}
