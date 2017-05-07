// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.GamesMetadata$LoadGameSearchSuggestionsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$GameSearchSuggestionsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<GamesMetadata$LoadGameSearchSuggestionsResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$GameSearchSuggestionsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<GamesMetadata$LoadGameSearchSuggestionsResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void l(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadGameSearchSuggestionsResultImpl(dataHolder));
    }
}
