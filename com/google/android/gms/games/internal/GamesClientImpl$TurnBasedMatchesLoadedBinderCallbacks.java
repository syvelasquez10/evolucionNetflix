// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchesLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult> XI;
    
    public GamesClientImpl$TurnBasedMatchesLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult> baseImplementation$b) {
        this.Wr = wr;
        this.XI = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void b(final int n, final Bundle bundle) {
        bundle.setClassLoader(this.getClass().getClassLoader());
        this.XI.b(new GamesClientImpl$LoadMatchesResultImpl(new Status(n), bundle));
    }
}
