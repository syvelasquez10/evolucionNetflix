// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.GamesMetadata$LoadGameInstancesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$GameInstancesLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<GamesMetadata$LoadGameInstancesResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$GameInstancesLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<GamesMetadata$LoadGameInstancesResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void k(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadGameInstancesResultImpl(dataHolder));
    }
}
