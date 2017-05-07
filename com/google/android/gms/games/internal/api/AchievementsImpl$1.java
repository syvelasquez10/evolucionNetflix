// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class AchievementsImpl$1 extends AchievementsImpl$LoadImpl
{
    final /* synthetic */ boolean XU;
    final /* synthetic */ AchievementsImpl XV;
    
    AchievementsImpl$1(final AchievementsImpl xv, final boolean xu) {
        this.XV = xv;
        this.XU = xu;
        super((AchievementsImpl$1)null);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.c((BaseImplementation$b<Achievements$LoadAchievementsResult>)this, this.XU);
    }
}
