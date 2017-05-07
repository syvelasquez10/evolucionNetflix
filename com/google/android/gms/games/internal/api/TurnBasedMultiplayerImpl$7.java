// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LeaveMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$7 extends TurnBasedMultiplayerImpl$LeaveMatchImpl
{
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    final /* synthetic */ String ZP;
    final /* synthetic */ String ZT;
    
    TurnBasedMultiplayerImpl$7(final TurnBasedMultiplayerImpl zo, final String zp, final String zt) {
        this.ZO = zo;
        this.ZP = zp;
        this.ZT = zt;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<TurnBasedMultiplayer$LeaveMatchResult>)this, this.ZP, this.ZT);
    }
}
