// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.g;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$AcceptQuestResultImpl extends a implements Quests$AcceptQuestResult
{
    private final Quest Wt;
    
    GamesClientImpl$AcceptQuestResultImpl(DataHolder dataHolder) {
        super(dataHolder);
        dataHolder = (DataHolder)new QuestBuffer(dataHolder);
        try {
            if (((g)dataHolder).getCount() > 0) {
                this.Wt = new QuestEntity(((g<Quest>)dataHolder).get(0));
            }
            else {
                this.Wt = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    @Override
    public Quest getQuest() {
        return this.Wt;
    }
}
