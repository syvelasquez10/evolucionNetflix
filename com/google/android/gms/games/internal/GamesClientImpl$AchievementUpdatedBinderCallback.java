// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$AchievementUpdatedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Achievements$UpdateAchievementResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$AchievementUpdatedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Achievements$UpdateAchievementResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void g(final int n, final String s) {
        this.De.b(new GamesClientImpl$UpdateAchievementResultImpl(n, s));
    }
}
