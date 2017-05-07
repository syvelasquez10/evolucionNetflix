// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$9 extends TurnBasedMultiplayerImpl$LoadMatchesImpl
{
    final /* synthetic */ TurnBasedMultiplayerImpl ZO;
    final /* synthetic */ int ZQ;
    final /* synthetic */ int[] ZR;
    
    TurnBasedMultiplayerImpl$9(final TurnBasedMultiplayerImpl zo, final int zq, final int[] zr) {
        this.ZO = zo;
        this.ZQ = zq;
        this.ZR = zr;
        super((TurnBasedMultiplayerImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult>)this, this.ZQ, this.ZR);
    }
}
