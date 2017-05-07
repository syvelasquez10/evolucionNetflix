// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.quest.QuestUpdateListener;

final class GamesClientImpl$QuestUpdateBinderCallback extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final QuestUpdateListener Xk;
    
    GamesClientImpl$QuestUpdateBinderCallback(final GamesClientImpl wr, final QuestUpdateListener xk) {
        this.Wr = wr;
        this.Xk = xk;
    }
    
    private Quest S(final DataHolder dataHolder) {
        final QuestBuffer questBuffer = new QuestBuffer(dataHolder);
        Quest quest = null;
        try {
            if (questBuffer.getCount() > 0) {
                quest = questBuffer.get(0).freeze();
            }
            return quest;
        }
        finally {
            questBuffer.release();
        }
    }
    
    @Override
    public void M(final DataHolder dataHolder) {
        final Quest s = this.S(dataHolder);
        if (s != null) {
            this.Wr.a(new GamesClientImpl$QuestCompletedCallback(this.Wr, this.Xk, s));
        }
    }
}
