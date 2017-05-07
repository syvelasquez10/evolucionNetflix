// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;

class QuestsImpl$AcceptImpl$1 implements Quests$AcceptQuestResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ QuestsImpl$AcceptImpl Zl;
    
    QuestsImpl$AcceptImpl$1(final QuestsImpl$AcceptImpl zl, final Status cw) {
        this.Zl = zl;
        this.CW = cw;
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
