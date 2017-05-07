// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGameSearchSuggestionsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class GamesMetadataImpl$LoadGameSearchSuggestionsImpl extends Games$BaseGamesApiMethodImpl<GamesMetadata$LoadGameSearchSuggestionsResult>
{
    public GamesMetadata$LoadGameSearchSuggestionsResult R(final Status status) {
        return new GamesMetadataImpl$LoadGameSearchSuggestionsImpl$1(this, status);
    }
}
