// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$7 extends LeaderboardsImpl$SubmitScoreImpl
{
    final /* synthetic */ long YF;
    final /* synthetic */ String YG;
    final /* synthetic */ LeaderboardsImpl Yy;
    final /* synthetic */ String Yz;
    
    LeaderboardsImpl$7(final LeaderboardsImpl yy, final String yz, final long yf, final String yg) {
        this.Yy = yy;
        this.Yz = yz;
        this.YF = yf;
        this.YG = yg;
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$SubmitScoreResult>)this, this.Yz, this.YF, this.YG);
    }
}
