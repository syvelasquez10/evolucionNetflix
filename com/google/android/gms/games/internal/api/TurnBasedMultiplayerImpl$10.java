// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$10 extends TurnBasedMultiplayerImpl$LoadMatchImpl
{
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    final /* synthetic */ String ZP;
    
    TurnBasedMultiplayerImpl$10(final TurnBasedMultiplayerImpl zo, final String zp) {
        this.ZO = zo;
        this.ZP = zp;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.h((BaseImplementation$b<TurnBasedMultiplayer$LoadMatchResult>)this, this.ZP);
    }
}
