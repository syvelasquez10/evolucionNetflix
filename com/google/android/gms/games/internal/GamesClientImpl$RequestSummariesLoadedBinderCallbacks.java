// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.request.Requests$LoadRequestSummariesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$RequestSummariesLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Requests$LoadRequestSummariesResult> Xt;
    
    public GamesClientImpl$RequestSummariesLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Requests$LoadRequestSummariesResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xt = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void H(final DataHolder dataHolder) {
        this.Xt.b(new GamesClientImpl$LoadRequestSummariesResultImpl(dataHolder));
    }
}
