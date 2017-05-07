// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class LeaderboardsImpl$LoadPlayerScoreImpl extends Games$BaseGamesApiMethodImpl<Leaderboards$LoadPlayerScoreResult>
{
    public Leaderboards$LoadPlayerScoreResult V(final Status status) {
        return new LeaderboardsImpl$LoadPlayerScoreImpl$1(this, status);
    }
}
