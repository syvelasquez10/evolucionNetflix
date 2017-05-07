// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.quest.Quests$ClaimMilestoneResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$QuestMilestoneClaimBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Quests$ClaimMilestoneResult> Xi;
    private final String Xj;
    
    public GamesClientImpl$QuestMilestoneClaimBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Quests$ClaimMilestoneResult> baseImplementation$b, final String s) {
        this.Wr = wr;
        this.Xi = n.b(baseImplementation$b, "Holder must not be null");
        this.Xj = n.b(s, (Object)"MilestoneId must not be null");
    }
    
    @Override
    public void K(final DataHolder dataHolder) {
        this.Xi.b(new GamesClientImpl$ClaimMilestoneResultImpl(dataHolder, this.Xj));
    }
}
