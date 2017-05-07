// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.common.api.GoogleApiClient;

public interface Players
{
    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
    
    Player getCurrentPlayer(final GoogleApiClient p0);
    
    String getCurrentPlayerId(final GoogleApiClient p0);
    
    Intent getPlayerSearchIntent(final GoogleApiClient p0);
    
    PendingResult<Players$LoadPlayersResult> loadConnectedPlayers(final GoogleApiClient p0, final boolean p1);
    
    PendingResult<Players$LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient p0, final int p1, final boolean p2);
    
    PendingResult<Players$LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient p0, final int p1);
    
    PendingResult<Players$LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient p0, final int p1);
    
    PendingResult<Players$LoadPlayersResult> loadPlayer(final GoogleApiClient p0, final String p1);
    
    PendingResult<Players$LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient p0, final int p1, final boolean p2);
}
