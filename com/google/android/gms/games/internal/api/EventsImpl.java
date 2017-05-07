// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation;
import com.google.android.gms.common.api.PendingResult;
import android.os.RemoteException;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.event.Events;

public final class EventsImpl implements Events
{
    @Override
    public void increment(final GoogleApiClient googleApiClient, final String s, final int n) {
        final GamesClientImpl d = Games.d(googleApiClient);
        if (d.isConnected()) {
            d.n(s, n);
            return;
        }
        googleApiClient.b(new UpdateImpl() {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.n(s, n);
            }
        });
    }
    
    @Override
    public PendingResult<LoadEventsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<LoadEventsResult>)new LoadImpl() {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.d((BaseImplementation.b<LoadEventsResult>)this, b);
            }
        });
    }
    
    @Override
    public PendingResult<LoadEventsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return googleApiClient.a((PendingResult<LoadEventsResult>)new LoadImpl() {
            public void a(final GamesClientImpl gamesClientImpl) {
                gamesClientImpl.a((BaseImplementation.b<LoadEventsResult>)this, b, array);
            }
        });
    }
    
    private abstract static class LoadImpl extends BaseGamesApiMethodImpl<LoadEventsResult>
    {
        public LoadEventsResult O(final Status status) {
            return new LoadEventsResult() {
                @Override
                public EventBuffer getEvents() {
                    return new EventBuffer(DataHolder.as(14));
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
    
    private abstract static class UpdateImpl extends BaseGamesApiMethodImpl<Result>
    {
        public Result c(final Status status) {
            return new Result() {
                @Override
                public Status getStatus() {
                    return status;
                }
            };
        }
    }
}
