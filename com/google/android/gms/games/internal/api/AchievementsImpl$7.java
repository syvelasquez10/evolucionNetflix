// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class AchievementsImpl$7 extends AchievementsImpl$UpdateImpl
{
    final /* synthetic */ AchievementsImpl XV;
    final /* synthetic */ String XY;
    final /* synthetic */ int XZ;
    
    AchievementsImpl$7(final AchievementsImpl xv, final String s, final String xy, final int xz) {
        this.XV = xv;
        this.XY = xy;
        this.XZ = xz;
        super(s);
    }
    
    public void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.a((BaseImplementation$b<Achievements$UpdateAchievementResult>)this, this.XY, this.XZ);
    }
}
