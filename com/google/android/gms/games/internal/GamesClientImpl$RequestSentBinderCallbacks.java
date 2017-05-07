// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.request.Requests$SendRequestResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$RequestSentBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Requests$SendRequestResult> Xs;
    
    public GamesClientImpl$RequestSentBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Requests$SendRequestResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xs = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void G(final DataHolder dataHolder) {
        this.Xs.b(new GamesClientImpl$SendRequestResultImpl(dataHolder));
    }
}
