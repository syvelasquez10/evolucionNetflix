// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$SignOutCompleteBinderCallbacks extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Status> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    public GamesClientImpl$SignOutCompleteBinderCallbacks(final GamesClientImpl wr, final BaseImplementation$b<Status> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void fq() {
        this.De.b(new Status(0));
    }
}
