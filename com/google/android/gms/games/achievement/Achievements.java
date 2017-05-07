// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.achievement;

import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Achievements
{
    Intent getAchievementsIntent(final GoogleApiClient p0);
    
    void increment(final GoogleApiClient p0, final String p1, final int p2);
    
    PendingResult<Achievements$UpdateAchievementResult> incrementImmediate(final GoogleApiClient p0, final String p1, final int p2);
    
    PendingResult<Achievements$LoadAchievementsResult> load(final GoogleApiClient p0, final boolean p1);
    
    void reveal(final GoogleApiClient p0, final String p1);
    
    PendingResult<Achievements$UpdateAchievementResult> revealImmediate(final GoogleApiClient p0, final String p1);
    
    void setSteps(final GoogleApiClient p0, final String p1, final int p2);
    
    PendingResult<Achievements$UpdateAchievementResult> setStepsImmediate(final GoogleApiClient p0, final String p1, final int p2);
    
    void unlock(final GoogleApiClient p0, final String p1);
    
    PendingResult<Achievements$UpdateAchievementResult> unlockImmediate(final GoogleApiClient p0, final String p1);
}
