// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface GamesMetadata
{
    Game getCurrentGame(final GoogleApiClient p0);
    
    PendingResult<GamesMetadata$LoadGamesResult> loadGame(final GoogleApiClient p0);
}
