// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$CancelMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$8 extends TurnBasedMultiplayerImpl$CancelMatchImpl
{
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    final /* synthetic */ String ZP;
    
    TurnBasedMultiplayerImpl$8(final TurnBasedMultiplayerImpl zo, final String s, final String zp) {
        this.ZO = zo;
        this.ZP = zp;
        super(s);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.g((BaseImplementation$b<TurnBasedMultiplayer$CancelMatchResult>)this, this.ZP);
    }
}
