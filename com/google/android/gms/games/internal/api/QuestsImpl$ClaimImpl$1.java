// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;

class QuestsImpl$ClaimImpl$1 implements Quests$ClaimMilestoneResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ QuestsImpl$ClaimImpl Zm;
    
    QuestsImpl$ClaimImpl$1(final QuestsImpl$ClaimImpl zm, final Status cw) {
        this.Zm = zm;
        this.CW = cw;
    }
    
    @Override
    public Milestone getMilestone() {
        return null;
    }
    
    @Override
    public Quest getQuest() {
        return null;
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
}
