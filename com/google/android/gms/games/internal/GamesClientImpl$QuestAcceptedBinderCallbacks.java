// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.quest.Quests$AcceptQuestResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$QuestAcceptedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Quests$AcceptQuestResult> Xh;
    
    public GamesClientImpl$QuestAcceptedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Quests$AcceptQuestResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xh = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void L(final DataHolder dataHolder) {
        this.Xh.b(new GamesClientImpl$AcceptQuestResultImpl(dataHolder));
    }
}
