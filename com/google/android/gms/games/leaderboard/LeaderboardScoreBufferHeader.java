// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader
{
    private final Bundle MZ;
    
    public LeaderboardScoreBufferHeader(final Bundle bundle) {
        Bundle mz = bundle;
        if (bundle == null) {
            mz = new Bundle();
        }
        this.MZ = mz;
    }
    
    public Bundle lz() {
        return this.MZ;
    }
    
    public static final class Builder
    {
    }
}
