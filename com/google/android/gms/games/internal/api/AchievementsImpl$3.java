// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class AchievementsImpl$3 extends AchievementsImpl$UpdateImpl
{
    final /* synthetic */ AchievementsImpl XV;
    final /* synthetic */ String XY;
    
    AchievementsImpl$3(final AchievementsImpl xv, final String s, final String xy) {
        this.XV = xv;
        this.XY = xy;
        super(s);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.b((BaseImplementation$b<Achievements$UpdateAchievementResult>)this, this.XY);
    }
}
