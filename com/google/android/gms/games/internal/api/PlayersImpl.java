// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
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
        return Games.c(googleApiClient).gn();
    }
    
    @Override
    public String getCurrentPlayerId(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).gm();
    }
    
    @Override
    public Intent getPlayerSearchIntent(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).gw();
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadConnectedPlayers(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, n, false, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, n, true, false);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, "playedWith", n, true, false);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadPlayer(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((d<LoadPlayersResult>)this, "playedWith", n, false, b);
            }
        });
    }
    
    private abstract static class LoadExtendedPlayersImpl extends BaseGamesApiMethodImpl<LoadExtendedPlayersResult>
    {
        public LoadExtendedPlayersResult K(final Status status) {
            return new LoadExtendedPlayersResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
    
    private abstract static class LoadOwnerCoverPhotoUrisImpl extends BaseGamesApiMethodImpl<LoadOwnerCoverPhotoUrisResult>
    {
        public LoadOwnerCoverPhotoUrisResult L(final Status status) {
            return new LoadOwnerCoverPhotoUrisResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadPlayersImpl extends BaseGamesApiMethodImpl<LoadPlayersResult>
    {
        public LoadPlayersResult M(final Status status) {
            return new LoadPlayersResult() {
                @Override
                public PlayerBuffer getPlayers() {
                    return new PlayerBuffer(DataHolder.empty(14));
                }
                
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public void release() {
                }
            };
        }
    }
}
