// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$1 extends LeaderboardsImpl$LoadMetadataImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ LeaderboardsImpl Yy;
    
    LeaderboardsImpl$1(final LeaderboardsImpl yy, final boolean xu) {
        this.Yy = yy;
        this.XU = xu;
        super((LeaderboardsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Leaderboards$LeaderboardMetadataResult>)this, this.XU);
    }
}
