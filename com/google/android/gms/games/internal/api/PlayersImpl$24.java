// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class PlayersImpl$24 extends PlayersImpl$LoadPlayersImpl
{
    final /* synthetic */ String YW;
    final /* synthetic */ int Yl;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.g((BaseImplementation$b<Players$LoadPlayersResult>)this, this.YW, this.Yl, true, false);
    }
}
