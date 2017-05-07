// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.GamesMetadata$LoadGameInstancesResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class GamesMetadataImpl$LoadGameInstancesImpl extends Games$BaseGamesApiMethodImpl<GamesMetadata$LoadGameInstancesResult>
{
    public GamesMetadata$LoadGameInstancesResult Q(final Status status) {
        return new GamesMetadataImpl$LoadGameInstancesImpl$1(this, status);
    }
}
