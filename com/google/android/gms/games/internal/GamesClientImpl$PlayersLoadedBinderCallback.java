// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$PlayersLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Players$LoadPlayersResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$PlayersLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Players$LoadPlayersResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void g(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadPlayersResultImpl(dataHolder));
    }
    
    @Override
    public void h(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadPlayersResultImpl(dataHolder));
    }
}
