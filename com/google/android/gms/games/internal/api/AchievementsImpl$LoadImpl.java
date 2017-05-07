// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.achievement.Achievements$LoadAchievementsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class AchievementsImpl$LoadImpl extends Games$BaseGamesApiMethodImpl<Achievements$LoadAchievementsResult>
{
    public Achievements$LoadAchievementsResult J(final Status status) {
        return new AchievementsImpl$LoadImpl$1(this, status);
    }
}
