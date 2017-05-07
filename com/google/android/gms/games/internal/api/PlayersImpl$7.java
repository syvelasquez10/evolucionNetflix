// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class PlayersImpl$7 extends PlayersImpl$LoadPlayersImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ PlayersImpl YT;
    
    PlayersImpl$7(final PlayersImpl yt, final boolean xu) {
        this.YT = yt;
        this.XU = xu;
        super((PlayersImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Players$LoadPlayersResult>)this, this.XU);
    }
}
