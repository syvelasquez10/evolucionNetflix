// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$2 extends LeaderboardsImpl$LoadMetadataImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ LeaderboardsImpl Yy;
    final /* synthetic */ String Yz;
    
    LeaderboardsImpl$2(final LeaderboardsImpl yy, final String yz, final boolean xu) {
        this.Yy = yy;
        this.Yz = yz;
        this.XU = xu;
        super((LeaderboardsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$LeaderboardMetadataResult>)this, this.Yz, this.XU);
    }
}
