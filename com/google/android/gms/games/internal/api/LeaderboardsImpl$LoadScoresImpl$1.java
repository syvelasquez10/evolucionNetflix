// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;

class LeaderboardsImpl$LoadScoresImpl$1 implements Leaderboards$LoadScoresResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ LeaderboardsImpl$LoadScoresImpl YJ;
    
    LeaderboardsImpl$LoadScoresImpl$1(final LeaderboardsImpl$LoadScoresImpl yj, final Status cw) {
        this.YJ = yj;
        this.CW = cw;
    }
    
    @Override
    public Leaderboard getLeaderboard() {
        return null;
    }
    
    @Override
    public LeaderboardScoreBuffer getScores() {
        return new LeaderboardScoreBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
