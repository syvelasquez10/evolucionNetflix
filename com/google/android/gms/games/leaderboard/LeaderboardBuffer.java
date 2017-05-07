// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.d;

public final class LeaderboardBuffer extends d<Leaderboard>
{
    public LeaderboardBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    protected Leaderboard getEntry(final int n, final int n2) {
        return new LeaderboardRef(this.BB, n, n2);
    }
    
    @Override
    protected String getPrimaryDataMarkerColumn() {
        return "external_leaderboard_id";
    }
}
