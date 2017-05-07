// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.data.g;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadScoresResultImpl extends a implements Leaderboards$LoadScoresResult
{
    private final LeaderboardEntity WR;
    private final LeaderboardScoreBuffer WS;
    
    GamesClientImpl$LoadScoresResultImpl(DataHolder dataHolder, final DataHolder dataHolder2) {
        super(dataHolder2);
        dataHolder = (DataHolder)new LeaderboardBuffer(dataHolder);
        try {
            if (((g)dataHolder).getCount() > 0) {
                this.WR = ((Freezable<LeaderboardEntity>)((g<Leaderboard>)dataHolder).get(0)).freeze();
            }
            else {
                this.WR = null;
            }
            ((DataBuffer)dataHolder).release();
            this.WS = new LeaderboardScoreBuffer(dataHolder2);
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    @Override
    public Leaderboard getLeaderboard() {
        return this.WR;
    }
    
    @Override
    public LeaderboardScoreBuffer getScores() {
        return this.WS;
    }
}
