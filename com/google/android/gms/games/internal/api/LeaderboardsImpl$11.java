// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$11 extends LeaderboardsImpl$LoadScoresImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String XX;
    final /* synthetic */ int YA;
    final /* synthetic */ int YB;
    final /* synthetic */ int YC;
    final /* synthetic */ String Yz;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Leaderboards$LoadScoresResult>)this, this.XX, this.Yz, this.YA, this.YB, this.YC, this.XU);
    }
}
