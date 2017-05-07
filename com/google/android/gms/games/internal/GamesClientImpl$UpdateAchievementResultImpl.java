// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;

final class GamesClientImpl$UpdateAchievementResultImpl implements Achievements$UpdateAchievementResult
{
    private final Status CM;
    private final String VP;
    
    GamesClientImpl$UpdateAchievementResultImpl(final int n, final String vp) {
        this.CM = new Status(n);
        this.VP = vp;
    }
    
    @Override
    public String getAchievementId() {
        return this.VP;
    }
    
    @Override
    public Status getStatus() {
        return this.CM;
    }
}
