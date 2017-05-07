// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadPlayerScoreResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$PlayerLeaderboardScoreLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Leaderboards$LoadPlayerScoreResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$PlayerLeaderboardScoreLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Leaderboards$LoadPlayerScoreResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void E(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadPlayerScoreResultImpl(dataHolder));
    }
}
