// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Notifications$InboxCountResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$InboxCountsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Notifications$InboxCountResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$InboxCountsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Notifications$InboxCountResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void f(final int n, final Bundle bundle) {
        bundle.setClassLoader(this.getClass().getClassLoader());
        this.De.b(new GamesClientImpl$InboxCountResultImpl(new Status(n), bundle));
    }
}
