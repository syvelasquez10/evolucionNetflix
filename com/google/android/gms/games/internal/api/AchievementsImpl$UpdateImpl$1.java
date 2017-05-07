// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;

class AchievementsImpl$UpdateImpl$1 implements Achievements$UpdateAchievementResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ AchievementsImpl$UpdateImpl Yb;
    
    AchievementsImpl$UpdateImpl$1(final AchievementsImpl$UpdateImpl yb, final Status cw) {
        this.Yb = yb;
        this.CW = cw;
    }
    
    @Override
    public String getAchievementId() {
        return this.Yb.BL;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
