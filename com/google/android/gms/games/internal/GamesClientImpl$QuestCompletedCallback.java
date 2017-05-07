// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$QuestCompletedCallback extends d$b<QuestUpdateListener>
{
    final /* synthetic */ GamesClientImpl Wr;
    private final Quest Wt;
    
    GamesClientImpl$QuestCompletedCallback(final GamesClientImpl wr, final QuestUpdateListener questUpdateListener, final Quest wt) {
        this.Wr = wr;
        super(questUpdateListener);
        this.Wt = wt;
    }
    
    protected void b(final QuestUpdateListener questUpdateListener) {
        questUpdateListener.onQuestCompleted(this.Wt);
    }
    
    @Override
    protected void gT() {
    }
}
