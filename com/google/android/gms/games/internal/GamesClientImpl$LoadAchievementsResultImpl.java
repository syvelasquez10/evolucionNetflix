// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadAchievementsResultImpl extends a implements Achievements$LoadAchievementsResult
{
    private final AchievementBuffer WG;
    
    GamesClientImpl$LoadAchievementsResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WG = new AchievementBuffer(dataHolder);
    }
    
    @Override
    public AchievementBuffer getAchievements() {
        return this.WG;
    }
}
