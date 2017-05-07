// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadQuestsResultImpl extends a implements Quests$LoadQuestsResult
{
    private final DataHolder IC;
    
    GamesClientImpl$LoadQuestsResultImpl(final DataHolder ic) {
        super(ic);
        this.IC = ic;
    }
    
    @Override
    public QuestBuffer getQuests() {
        return new QuestBuffer(this.IC);
    }
}
