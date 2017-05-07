// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
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
    public PendingResult<LoadPlayersResult> loadConnectedPlayers(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadInvitablePlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, n, false, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadMoreInvitablePlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, n, true, false);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadMoreRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, "played_with", n, true, false);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadPlayer(final GoogleApiClient googleApiClient, final String s) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, s);
            }
        });
    }
    
    @Override
    public PendingResult<LoadPlayersResult> loadRecentlyPlayedWithPlayers(final GoogleApiClient googleApiClient, final int n, final boolean b) {
        return googleApiClient.a((PendingResult<LoadPlayersResult>)new LoadPlayersImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadPlayersResult>)this, "played_with", n, false, b);
            }
        });
    }
    
    private abstract static class LoadOwnerCoverPhotoUrisImpl extends BaseGamesApiMethodImpl<LoadOwnerCoverPhotoUrisResult>
    {
        public LoadOwnerCoverPhotoUrisResult ac(final Status status) {
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
        public LoadPlayersResult ad(final Status status) {
            return new LoadPlayersResult() {
                @Override
                public PlayerBuffer getPlayers() {
                    return new PlayerBuffer(DataHolder.as(14));
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
    
    private abstract static class LoadProfileSettingsResultImpl extends BaseGamesApiMethodImpl<LoadProfileSettingsResult>
    {
        protected LoadProfileSettingsResult ae(final Status status) {
            return new LoadProfileSettingsResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
                
                @Override
                public boolean isProfileVisible() {
                    return true;
                }
                
                @Override
                public boolean isVisibilityExplicitlySet() {
                    return false;
                }
            };
        }
    }
    
    private abstract static class LoadXpForGameCategoriesResultImpl extends BaseGamesApiMethodImpl<LoadXpForGameCategoriesResult>
    {
        public LoadXpForGameCategoriesResult af(final Status status) {
            return new LoadXpForGameCategoriesResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class LoadXpStreamResultImpl extends BaseGamesApiMethodImpl<LoadXpStreamResult>
    {
        public LoadXpStreamResult ag(final Status status) {
            return new LoadXpStreamResult() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
    
    private abstract static class UpdateProfileSettingsResultImpl extends BaseGamesApiMethodImpl<Status>
    {
        protected Status d(final Status status) {
            return status;
        }
    }
}
