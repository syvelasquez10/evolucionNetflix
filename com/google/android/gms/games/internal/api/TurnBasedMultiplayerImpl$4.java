// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$UpdateMatchResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.multiplayer.ParticipantResult;

class TurnBasedMultiplayerImpl$4 extends TurnBasedMultiplayerImpl$UpdateMatchImpl
{
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    final /* synthetic */ String ZP;
    final /* synthetic */ byte[] ZS;
    final /* synthetic */ String ZT;
    final /* synthetic */ ParticipantResult[] ZU;
    
    TurnBasedMultiplayerImpl$4(final TurnBasedMultiplayerImpl zo, final String zp, final byte[] zs, final String zt, final ParticipantResult[] zu) {
        this.ZO = zo;
        this.ZP = zp;
        this.ZS = zs;
        this.ZT = zt;
        this.ZU = zu;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<TurnBasedMultiplayer$UpdateMatchResult>)this, this.ZP, this.ZS, this.ZT, this.ZU);
    }
}
