// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchUpdatedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$UpdateMatchResult> XH;
    
    public GamesClientImpl$TurnBasedMatchUpdatedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$UpdateMatchResult> baseImplementation$b) {
        this.Wr = wr;
        this.XH = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void r(final DataHolder dataHolder) {
        this.XH.b(new GamesClientImpl$UpdateMatchResultImpl(dataHolder));
    }
}
