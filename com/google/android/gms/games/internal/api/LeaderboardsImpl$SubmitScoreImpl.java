// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

public abstract class LeaderboardsImpl$SubmitScoreImpl extends Games$BaseGamesApiMethodImpl<Leaderboards$SubmitScoreResult>
{
    public Leaderboards$SubmitScoreResult X(final Status status) {
        return new LeaderboardsImpl$SubmitScoreImpl$1(this, status);
    }
}
