// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements$UpdateAchievementResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.achievement.Achievements;

public final class AchievementsImpl implements Achievements
{
    @Override
    public Intent getAchievementsIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kc();
    }
    
    @Override
    public void increment(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.b(new AchievementsImpl$6(this, s, s, n));
    }
    
    @Override
    public PendingResult<Achievements$UpdateAchievementResult> incrementImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return googleApiClient.b((PendingResult<Achievements$UpdateAchievementResult>)new AchievementsImpl$7(this, s, s, n));
    }
    
    @Override
    public PendingResult<Achievements$LoadAchievementsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<Achievements$LoadAchievementsResult>)new AchievementsImpl$1(this, b));
    }
    
    @Override
    public void reveal(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.b(new AchievementsImpl$2(this, s, s));
    }
    
    @Override
    public PendingResult<Achievements$UpdateAchievementResult> revealImmediate(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Achievements$UpdateAchievementResult>)new AchievementsImpl$3(this, s, s));
    }
    
    @Override
    public void setSteps(final GoogleApiClient googleApiClient, final String s, final int n) {
        googleApiClient.b(new AchievementsImpl$8(this, s, s, n));
    }
    
    @Override
    public PendingResult<Achievements$UpdateAchievementResult> setStepsImmediate(final GoogleApiClient googleApiClient, final String s, final int n) {
        return googleApiClient.b((PendingResult<Achievements$UpdateAchievementResult>)new AchievementsImpl$9(this, s, s, n));
    }
    
    @Override
    public void unlock(final GoogleApiClient googleApiClient, final String s) {
        googleApiClient.b(new AchievementsImpl$4(this, s, s));
    }
    
    @Override
    public PendingResult<Achievements$UpdateAchievementResult> unlockImmediate(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.b((PendingResult<Achievements$UpdateAchievementResult>)new AchievementsImpl$5(this, s, s));
    }
}
