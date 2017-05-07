// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.GamesMetadata$LoadGamesResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.GamesMetadata;

public final class GamesMetadataImpl implements GamesMetadata
{
    @Override
    public Game getCurrentGame(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).ka();
    }
    
    @Override
    public PendingResult<GamesMetadata$LoadGamesResult> loadGame(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<GamesMetadata$LoadGamesResult>)new GamesMetadataImpl$1(this));
    }
}
