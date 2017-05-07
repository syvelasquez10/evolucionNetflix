// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Players$LoadXpStreamResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$PlayerXpStreamLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Players$LoadXpStreamResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$PlayerXpStreamLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Players$LoadXpStreamResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void P(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadXpStreamResultImpl(dataHolder));
    }
}
