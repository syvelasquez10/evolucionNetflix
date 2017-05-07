// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.quest.Quests$LoadQuestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$QuestsLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Quests$LoadQuestsResult> Xl;
    
    public GamesClientImpl$QuestsLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Quests$LoadQuestsResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xl = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void O(final DataHolder dataHolder) {
        this.Xl.b(new GamesClientImpl$LoadQuestsResultImpl(dataHolder));
    }
}
