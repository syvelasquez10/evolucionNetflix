// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class QuestsImpl$ClaimImpl extends Games$BaseGamesApiMethodImpl<Quests$ClaimMilestoneResult>
{
    public Quests$ClaimMilestoneResult ai(final Status status) {
        return new QuestsImpl$ClaimImpl$1(this, status);
    }
}
