// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class LeaderboardsImpl$LoadScoresImpl extends Games$BaseGamesApiMethodImpl<Leaderboards$LoadScoresResult>
{
    public Leaderboards$LoadScoresResult W(final Status status) {
        return new LeaderboardsImpl$LoadScoresImpl$1(this, status);
    }
}
