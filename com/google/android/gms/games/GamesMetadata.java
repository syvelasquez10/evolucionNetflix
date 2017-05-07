// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;

public interface GamesMetadata
{
    Game getCurrentGame(final GoogleApiClient p0);
    
    PendingResult<LoadGamesResult> loadGame(final GoogleApiClient p0);
    
    public interface LoadExtendedGamesResult extends Releasable, Result
    {
    }
    
    public interface LoadGameInstancesResult extends Releasable, Result
    {
    }
    
    public interface LoadGameSearchSuggestionsResult extends Releasable, Result
    {
    }
    
    public interface LoadGamesResult extends Releasable, Result
    {
        GameBuffer getGames();
    }
}
