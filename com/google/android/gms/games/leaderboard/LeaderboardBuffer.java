// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.g;

public final class LeaderboardBuffer extends g<Leaderboard>
{
    public LeaderboardBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    @Override
    protected String gE() {
        return "external_leaderboard_id";
    }
    
    protected Leaderboard i(final int n, final int n2) {
        return new LeaderboardRef(this.IC, n, n2);
    }
}
