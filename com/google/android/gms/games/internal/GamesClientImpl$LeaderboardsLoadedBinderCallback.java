// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.leaderboard.Leaderboards$LeaderboardMetadataResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$LeaderboardsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$LeaderboardsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Leaderboards$LeaderboardMetadataResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void e(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LeaderboardMetadataResultImpl(dataHolder));
    }
}
