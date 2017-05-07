// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class AchievementsImpl$UpdateImpl extends Games$BaseGamesApiMethodImpl<Achievements$UpdateAchievementResult>
{
    private final String BL;
    
    public AchievementsImpl$UpdateImpl(final String bl) {
        this.BL = bl;
    }
    
    public Achievements$UpdateAchievementResult K(final Status status) {
        return new AchievementsImpl$UpdateImpl$1(this, status);
    }
}
