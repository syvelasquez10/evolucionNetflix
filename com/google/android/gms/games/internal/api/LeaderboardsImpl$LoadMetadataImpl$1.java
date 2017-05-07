// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;

class LeaderboardsImpl$LoadMetadataImpl$1 implements Leaderboards$LeaderboardMetadataResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ LeaderboardsImpl$LoadMetadataImpl YH;
    
    LeaderboardsImpl$LoadMetadataImpl$1(final LeaderboardsImpl$LoadMetadataImpl yh, final Status cw) {
        this.YH = yh;
        this.CW = cw;
    }
    
    @Override
    public LeaderboardBuffer getLeaderboards() {
        return new LeaderboardBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
