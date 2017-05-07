// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;

class AchievementsImpl$LoadImpl$1 implements Achievements$LoadAchievementsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ AchievementsImpl$LoadImpl Ya;
    
    AchievementsImpl$LoadImpl$1(final AchievementsImpl$LoadImpl ya, final Status cw) {
        this.Ya = ya;
        this.CW = cw;
    }
    
    @Override
    public AchievementBuffer getAchievements() {
        return new AchievementBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
