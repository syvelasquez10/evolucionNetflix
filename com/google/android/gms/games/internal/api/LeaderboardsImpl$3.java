// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$3 extends LeaderboardsImpl$LoadPlayerScoreImpl
{
    final /* synthetic */ int YA;
    final /* synthetic */ int YB;
    final /* synthetic */ LeaderboardsImpl Yy;
    final /* synthetic */ String Yz;
    
    LeaderboardsImpl$3(final LeaderboardsImpl yy, final String yz, final int ya, final int yb) {
        this.Yy = yy;
        this.Yz = yz;
        this.YA = ya;
        this.YB = yb;
        super((LeaderboardsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$LoadPlayerScoreResult>)this, null, this.Yz, this.YA, this.YB);
    }
}
