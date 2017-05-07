// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$ContactSettingsUpdatedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Status> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$ContactSettingsUpdatedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Status> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void dy(final int n) {
        this.De.b(new Status(n));
    }
}
