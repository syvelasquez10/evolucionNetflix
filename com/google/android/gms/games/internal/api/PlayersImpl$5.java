// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class PlayersImpl$5 extends PlayersImpl$LoadPlayersImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ PlayersImpl YT;
    final /* synthetic */ int Yl;
    
    PlayersImpl$5(final PlayersImpl yt, final int yl, final boolean xu) {
        this.YT = yt;
        this.Yl = yl;
        this.XU = xu;
        super((PlayersImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Players$LoadPlayersResult>)this, "played_with", this.Yl, false, this.XU);
    }
}
