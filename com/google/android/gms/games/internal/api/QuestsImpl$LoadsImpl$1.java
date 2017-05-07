// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;

class QuestsImpl$LoadsImpl$1 implements Quests$LoadQuestsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ QuestsImpl$LoadsImpl Zn;
    
    QuestsImpl$LoadsImpl$1(final QuestsImpl$LoadsImpl zn, final Status cw) {
        this.Zn = zn;
        this.CW = cw;
    }
    
    @Override
    public QuestBuffer getQuests() {
        return new QuestBuffer(DataHolder.as(this.CW.getStatusCode()));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
