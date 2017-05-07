// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class QuestsImpl$AcceptImpl extends Games$BaseGamesApiMethodImpl<Quests$AcceptQuestResult>
{
    public Quests$AcceptQuestResult ah(final Status status) {
        return new QuestsImpl$AcceptImpl$1(this, status);
    }
}
