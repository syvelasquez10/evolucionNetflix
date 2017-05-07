// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class PlayersImpl$4 extends PlayersImpl$LoadPlayersImpl
{
    final /* synthetic */ PlayersImpl YT;
    final /* synthetic */ int Yl;
    
    PlayersImpl$4(final PlayersImpl yt, final int yl) {
        this.YT = yt;
        this.Yl = yl;
        super((PlayersImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Players$LoadPlayersResult>)this, this.Yl, true, false);
    }
}
