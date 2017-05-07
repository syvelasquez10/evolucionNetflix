// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$ExtendedGamesLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$ExtendedGamesLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<GamesMetadata$LoadExtendedGamesResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void j(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadExtendedGamesResultImpl(dataHolder));
    }
}
