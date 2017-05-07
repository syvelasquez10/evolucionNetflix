// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.request.Requests$LoadRequestsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$RequestsLoadedBinderCallbacks extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final BaseImplementation$b<Requests$LoadRequestsResult> Xu;
    
    public GamesClientImpl$RequestsLoadedBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Requests$LoadRequestsResult> baseImplementation$b) {
        this.Wr = wr;
        this.Xu = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void c(final int n, final Bundle bundle) {
        bundle.setClassLoader(this.getClass().getClassLoader());
        this.Xu.b(new GamesClientImpl$LoadRequestsResultImpl(new Status(n), bundle));
    }
}
