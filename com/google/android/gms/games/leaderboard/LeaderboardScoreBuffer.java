// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class LeaderboardScoreBuffer extends DataBuffer<LeaderboardScore>
{
    private final LeaderboardScoreBufferHeader LT;
    
    public LeaderboardScoreBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.LT = new LeaderboardScoreBufferHeader(dataHolder.getMetadata());
    }
    
    @Override
    public LeaderboardScore get(final int n) {
        return new LeaderboardScoreRef(this.BB, n);
    }
    
    public LeaderboardScoreBufferHeader hD() {
        return this.LT;
    }
}
