// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Achievements$LoadAchievementsResult extends Releasable, Result
{
    AchievementBuffer getAchievements();
}
