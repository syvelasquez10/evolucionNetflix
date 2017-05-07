// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$4 extends LeaderboardsImpl$LoadScoresImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ int YA;
    final /* synthetic */ int YB;
    final /* synthetic */ int YC;
    final /* synthetic */ LeaderboardsImpl Yy;
    final /* synthetic */ String Yz;
    
    LeaderboardsImpl$4(final LeaderboardsImpl yy, final String yz, final int ya, final int yb, final int yc, final boolean xu) {
        this.Yy = yy;
        this.Yz = yz;
        this.YA = ya;
        this.YB = yb;
        this.YC = yc;
        this.XU = xu;
        super((LeaderboardsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$LoadScoresResult>)this, this.Yz, this.YA, this.YB, this.YC, this.XU);
    }
}
