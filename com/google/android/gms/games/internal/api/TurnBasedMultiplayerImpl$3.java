// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$InitiateMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$3 extends TurnBasedMultiplayerImpl$InitiateMatchImpl
{
    final /* synthetic */ String Yw;
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    
    TurnBasedMultiplayerImpl$3(final TurnBasedMultiplayerImpl zo, final String yw) {
        this.ZO = zo;
        this.Yw = yw;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.e((BaseImplementation$b<TurnBasedMultiplayer$InitiateMatchResult>)this, this.Yw);
    }
}
