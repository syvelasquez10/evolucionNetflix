// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class LeaderboardsImpl$LoadMetadataImpl extends Games$BaseGamesApiMethodImpl<Leaderboards$LeaderboardMetadataResult>
{
    public Leaderboards$LeaderboardMetadataResult U(final Status status) {
        return new LeaderboardsImpl$LoadMetadataImpl$1(this, status);
    }
}
