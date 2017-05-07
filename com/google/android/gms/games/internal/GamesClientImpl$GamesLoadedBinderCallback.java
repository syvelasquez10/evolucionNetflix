// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$GamesLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<GamesMetadata$LoadGamesResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$GamesLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<GamesMetadata$LoadGamesResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void i(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadGamesResultImpl(dataHolder));
    }
}
