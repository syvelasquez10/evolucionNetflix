// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class QuestsImpl$LoadsImpl extends Games$BaseGamesApiMethodImpl<Quests$LoadQuestsResult>
{
    public Quests$LoadQuestsResult aj(final Status status) {
        return new QuestsImpl$LoadsImpl$1(this, status);
    }
}
