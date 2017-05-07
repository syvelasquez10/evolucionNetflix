// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Players
{
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    
    Player getCurrentPlayer(final GoogleApiClient p0);
    
    String getCurrentPlayerId(final GoogleApiClient p0);
    
    Intent getPlayerSearchIntent(final GoogleApiClient p0);
    
    PendingResult<LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient p0, final int p1, final boolean p2);
    
    PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient p0, final int p1);
    
    PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient p0, final int p1);
    
    PendingResult<LoadPlayersResult> loadPlayer(final GoogleApiClient p0, final String p1);
    
    PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient p0, final int p1, final boolean p2);
    
    public interface LoadPlayersResult extends Releasable, Result
    {
        PlayerBuffer getPlayers();
    }
}
