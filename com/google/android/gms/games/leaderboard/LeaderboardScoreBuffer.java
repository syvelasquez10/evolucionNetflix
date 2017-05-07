// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.DataBuffer;

public final class LeaderboardScoreBuffer extends DataBuffer<LeaderboardScore>
{
    private final c vI;
    
    public LeaderboardScoreBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.vI = new c(dataHolder.getMetadata());
    }
    
    public c dq() {
        return this.vI;
    }
    
    @Override
    public LeaderboardScore get(final int n) {
        return new e(this.nE, n);
    }
}
