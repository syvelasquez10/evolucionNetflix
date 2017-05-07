// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.leaderboard.Leaderboards$SubmitScoreResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SubmitScoreBinderCallbacks extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Leaderboards$SubmitScoreResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    public GamesClientImpl$SubmitScoreBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Leaderboards$SubmitScoreResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void f(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$SubmitScoreResultImpl(dataHolder));
    }
}
