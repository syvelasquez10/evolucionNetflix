// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.request.Requests$UpdateRequestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$RequestsUpdatedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Requests$UpdateRequestsResult> Xv;
    
    public GamesClientImpl$RequestsUpdatedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Requests$UpdateRequestsResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xv = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void F(final DataHolder dataHolder) {
        this.Xv.b(new GamesClientImpl$UpdateRequestsResultImpl(dataHolder));
    }
}
