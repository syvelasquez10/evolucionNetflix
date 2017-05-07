// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.data.g;
import java.util.List;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$ClaimMilestoneResultImpl extends a implements Quests$ClaimMilestoneResult
{
    private final Quest Wt;
    private final Milestone Wv;
    
    GamesClientImpl$ClaimMilestoneResultImpl(DataHolder dataHolder, final String s) {
        int i = 0;
        super(dataHolder);
        dataHolder = (DataHolder)new QuestBuffer(dataHolder);
        try {
            if (((g)dataHolder).getCount() > 0) {
                this.Wt = new QuestEntity(((g<Quest>)dataHolder).get(0));
                for (List<Milestone> lh = this.Wt.lH(); i < lh.size(); ++i) {
                    if (lh.get(i).getMilestoneId().equals(s)) {
                        this.Wv = lh.get(i);
                        return;
                    }
                }
                this.Wv = null;
            }
            else {
                this.Wv = null;
                this.Wt = null;
            }
        }
        finally {
            ((DataBuffer)dataHolder).release();
        }
    }
    
    @Override
    public Milestone getMilestone() {
        return this.Wv;
    }
    
    @Override
    public Quest getQuest() {
        return this.Wt;
    }
}
