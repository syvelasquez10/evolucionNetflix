// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;

class LeaderboardsImpl$LoadPlayerScoreImpl$1 implements Leaderboards$LoadPlayerScoreResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ LeaderboardsImpl$LoadPlayerScoreImpl YI;
    
    LeaderboardsImpl$LoadPlayerScoreImpl$1(final LeaderboardsImpl$LoadPlayerScoreImpl yi, final Status cw) {
        this.YI = yi;
        this.CW = cw;
    }
    
    @Override
    public LeaderboardScore getScore() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
