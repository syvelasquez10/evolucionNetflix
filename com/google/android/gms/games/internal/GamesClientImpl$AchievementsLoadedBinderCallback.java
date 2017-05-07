// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$AchievementsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Achievements$LoadAchievementsResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$AchievementsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Achievements$LoadAchievementsResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void c(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadAchievementsResultImpl(dataHolder));
    }
}
