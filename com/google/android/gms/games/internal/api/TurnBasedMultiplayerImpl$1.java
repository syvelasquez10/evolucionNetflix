// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;

class TurnBasedMultiplayerImpl$1 extends TurnBasedMultiplayerImpl$InitiateMatchImpl
{
    final /* synthetic */ TurnBasedMatchConfig ZN;
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    
    TurnBasedMultiplayerImpl$1(final TurnBasedMultiplayerImpl zo, final TurnBasedMatchConfig zn) {
        this.ZO = zo;
        this.ZN = zn;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult>)this, this.ZN);
    }
}
