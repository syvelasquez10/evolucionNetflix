// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer$LoadMatchesResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class TurnBasedMultiplayerImpl$13 extends TurnBasedMultiplayerImpl$LoadMatchesImpl
{
    final /* synthetic */ String XX;
    final /* synthetic */ int ZQ;
    final /* synthetic */ int[] ZR;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<TurnBasedMultiplayer$LoadMatchesResult>)this, this.XX, this.ZQ, this.ZR);
    }
}
