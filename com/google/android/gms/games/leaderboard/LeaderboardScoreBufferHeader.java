// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.leaderboard;

import android.os.Bundle;

public final class LeaderboardScoreBufferHeader
{
    private final Bundle Jf;
    
    public LeaderboardScoreBufferHeader(final Bundle bundle) {
        Bundle jf = bundle;
        if (bundle == null) {
            jf = new Bundle();
        }
        this.Jf = jf;
    }
    
    public Bundle hE() {
        return this.Jf;
    }
    
    public static final class Builder
    {
    }
}
