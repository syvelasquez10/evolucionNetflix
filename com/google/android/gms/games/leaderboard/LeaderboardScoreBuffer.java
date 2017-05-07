// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class LeaderboardScoreBuffer extends DataBuffer<LeaderboardScore>
{
    private final LeaderboardScoreBufferHeader abn;
    
    public LeaderboardScoreBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.abn = new LeaderboardScoreBufferHeader(dataHolder.gz());
    }
    
    @Override
    public LeaderboardScore get(final int n) {
        return new LeaderboardScoreRef(this.IC, n);
    }
    
    public LeaderboardScoreBufferHeader ly() {
        return this.abn;
    }
}
