// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadPlayerScoreResultImpl extends a implements Leaderboards$LoadPlayerScoreResult
{
    private final LeaderboardScoreEntity WN;
    
    GamesClientImpl$LoadPlayerScoreResultImpl(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder = (DataHolder)new LeaderboardScoreBuffer(dataHolder);
        try {
            if (((DataBuffer)dataHolder).getCount() > 0) {
                this.WN = ((Freezable<LeaderboardScoreEntity>)((LeaderboardScoreBuffer)dataHolder).get(0)).freeze();
            }
            else {
                this.WN = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    @Override
    public LeaderboardScore getScore() {
        return this.WN;
    }
}
