// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class GamesMetadataImpl$LoadGamesImpl extends Games$BaseGamesApiMethodImpl<GamesMetadata$LoadGamesResult>
{
    public GamesMetadata$LoadGamesResult S(final Status status) {
        return new GamesMetadataImpl$LoadGamesImpl$1(this, status);
    }
}
