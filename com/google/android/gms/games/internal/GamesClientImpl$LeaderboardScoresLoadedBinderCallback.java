// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.leaderboard.Leaderboards$LoadScoresResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$LeaderboardScoresLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Leaderboards$LoadScoresResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$LeaderboardScoresLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Leaderboards$LoadScoresResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void a(final DataHolder dataHolder, final DataHolder dataHolder2) {
        this.De.b(new GamesClientImpl$LoadScoresResultImpl(dataHolder, dataHolder2));
    }
}
