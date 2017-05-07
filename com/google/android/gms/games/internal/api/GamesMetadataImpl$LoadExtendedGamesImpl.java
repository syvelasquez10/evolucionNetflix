// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadExtendedGamesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class GamesMetadataImpl$LoadExtendedGamesImpl extends Games$BaseGamesApiMethodImpl<GamesMetadata$LoadExtendedGamesResult>
{
    public GamesMetadata$LoadExtendedGamesResult P(final Status status) {
        return new GamesMetadataImpl$LoadExtendedGamesImpl$1(this, status);
    }
}
