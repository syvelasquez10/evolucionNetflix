// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$8 extends LeaderboardsImpl$LoadMetadataImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String XX;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.c((BaseImplementation$b<Leaderboards$LeaderboardMetadataResult>)this, this.XX, this.XU);
    }
}
