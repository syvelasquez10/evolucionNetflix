// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.Players$LoadPlayersResult;
import com.google.android.gms.common.api.PendingResult;
import android.content.Intent;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Player;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Players;

public final class PlayersImpl implements Players
{
    @Override
    public Player getCurrentPlayer(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).jZ();
    }
    
    @Override
    public String getCurrentPlayerId(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).jY();
    }
    
    @Override
    public Intent getPlayerSearchIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).kj();
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadConnectedPlayers(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$7(this, b));
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$3(this, n, b));
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$4(this, n));
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$6(this, n));
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadPlayer(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$1(this, s));
    }
    
    @Override
    public PendingResult<Players$LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<Players$LoadPlayersResult>)new PlayersImpl$5(this, n, b));
    }
}
