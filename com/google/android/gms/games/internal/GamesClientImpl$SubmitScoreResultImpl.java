// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$SubmitScoreResultImpl extends a implements Leaderboards$SubmitScoreResult
{
    private final ScoreSubmissionData XC;
    
    public GamesClientImpl$SubmitScoreResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        try {
            this.XC = new ScoreSubmissionData(dataHolder);
        }
        finally {
            dataHolder.close();
        }
    }
    
    @Override
    public ScoreSubmissionData getScoreData() {
        return this.XC;
    }
}
