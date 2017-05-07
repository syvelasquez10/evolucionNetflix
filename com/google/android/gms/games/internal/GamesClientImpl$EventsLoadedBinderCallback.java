// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.event.Events$LoadEventsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$EventsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Events$LoadEventsResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$EventsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Events$LoadEventsResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void d(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadEventResultImpl(dataHolder));
    }
}
