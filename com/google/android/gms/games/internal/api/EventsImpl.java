// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.games.internal.GamesClientImpl;
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
        googleApiClient.b(new EventsImpl$3(this, s, n));
    }
    
    @Override
    public PendingResult<Events$LoadEventsResult> load(final GoogleApiClient googleApiClient, final boolean b) {
        return googleApiClient.a((PendingResult<Events$LoadEventsResult>)new EventsImpl$2(this, b));
    }
    
    @Override
    public PendingResult<Events$LoadEventsResult> loadByIds(final GoogleApiClient googleApiClient, final boolean b, final String... array) {
        return googleApiClient.a((PendingResult<Events$LoadEventsResult>)new EventsImpl$1(this, b, array));
    }
}
