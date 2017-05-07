// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;

class LeaderboardsImpl$6 extends LeaderboardsImpl$LoadScoresImpl
{
    final /* synthetic */ int YC;
    final /* synthetic */ LeaderboardScoreBuffer YD;
    final /* synthetic */ int YE;
    final /* synthetic */ LeaderboardsImpl Yy;
    
    LeaderboardsImpl$6(final LeaderboardsImpl yy, final LeaderboardScoreBuffer yd, final int yc, final int ye) {
        this.Yy = yy;
        this.YD = yd;
        this.YC = yc;
        this.YE = ye;
        super((LeaderboardsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$LoadScoresResult>)this, this.YD, this.YC, this.YE);
    }
}
