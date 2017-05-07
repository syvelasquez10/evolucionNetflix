// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LeaveMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$TurnBasedMatchLeftBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<TurnBasedMultiplayer$LeaveMatchResult> XF;
    
    public GamesClientImpl$TurnBasedMatchLeftBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<TurnBasedMultiplayer$LeaveMatchResult> baseImplementation$b) {
        this.Wr = wr;
        this.XF = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void s(final DataHolder dataHolder) {
        this.XF.b(new GamesClientImpl$LeaveMatchResultImpl(dataHolder));
    }
}
