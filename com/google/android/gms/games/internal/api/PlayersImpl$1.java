// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class PlayersImpl$1 extends PlayersImpl$LoadPlayersImpl
{
    final /* synthetic */ String XW;
    final /* synthetic */ PlayersImpl YT;
    
    PlayersImpl$1(final PlayersImpl yt, final String xw) {
        this.YT = yt;
        this.XW = xw;
        super((PlayersImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Players$LoadPlayersResult>)this, this.XW);
    }
}
