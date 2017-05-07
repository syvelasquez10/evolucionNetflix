// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class LeaderboardsImpl$9 extends LeaderboardsImpl$LoadMetadataImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ String XX;
    final /* synthetic */ String Yz;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Leaderboards$LeaderboardMetadataResult>)this, this.XX, this.Yz, this.XU);
    }
}
