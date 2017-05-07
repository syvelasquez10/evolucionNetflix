// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.GamesMetadata;

public final class GamesMetadataImpl implements GamesMetadata
{
    @Override
    public Game getCurrentGame(final GoogleApiClient googleApiClient) {
        return Games.c(googleApiClient).go();
    }
    
    @Override
    public PendingResult<LoadGamesResult> loadGame(final GoogleApiClient googleApiClient) {
        return googleApiClient.a((PendingResult<LoadGamesResult>)new LoadGamesImpl() {
            protected void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.g((d<LoadGamesResult>)this);
            }
        });
    }
    
    private abstract static class LoadExtendedGamesImpl extends BaseGamesApiMethodImpl<LoadExtendedGamesResult>
    {
        public LoadExtendedGamesResult y(final Status status) {
            return new LoadExtendedGamesResult() {
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
    
    private abstract static class LoadGameInstancesImpl extends BaseGamesApiMethodImpl<LoadGameInstancesResult>
    {
        public LoadGameInstancesResult z(final Status status) {
            return new LoadGameInstancesResult() {
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
    
    private abstract static class LoadGameSearchSuggestionsImpl extends BaseGamesApiMethodImpl<LoadGameSearchSuggestionsResult>
    {
        public LoadGameSearchSuggestionsResult A(final Status status) {
            return new LoadGameSearchSuggestionsResult() {
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
    
    private abstract static class LoadGamesImpl extends BaseGamesApiMethodImpl<LoadGamesResult>
    {
        public LoadGamesResult B(final Status status) {
            return new LoadGamesResult() {
                @Override
                public GameBuffer getGames() {
                    return new GameBuffer(DataHolder.empty(14));
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
