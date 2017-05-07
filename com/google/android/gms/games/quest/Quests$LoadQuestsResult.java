// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.quest;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;

public interface Quests$LoadQuestsResult extends Releasable, Result
{
    QuestBuffer getQuests();
}
