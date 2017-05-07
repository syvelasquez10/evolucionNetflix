// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;

class LeaderboardsImpl$SubmitScoreImpl$1 implements Leaderboards$SubmitScoreResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ LeaderboardsImpl$SubmitScoreImpl YK;
    
    LeaderboardsImpl$SubmitScoreImpl$1(final LeaderboardsImpl$SubmitScoreImpl yk, final Status cw) {
        this.YK = yk;
        this.CW = cw;
    }
    
    @Override
    public ScoreSubmissionData getScoreData() {
        return new ScoreSubmissionData(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
