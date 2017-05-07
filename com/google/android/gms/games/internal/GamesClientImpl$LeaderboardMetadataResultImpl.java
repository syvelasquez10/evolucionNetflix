// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LeaderboardMetadataResultImpl extends a implements Leaderboards$LeaderboardMetadataResult
{
    private final LeaderboardBuffer WE;
    
    GamesClientImpl$LeaderboardMetadataResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WE = new LeaderboardBuffer(dataHolder);
    }
    
    @Override
    public LeaderboardBuffer getLeaderboards() {
        return this.WE;
    }
}
