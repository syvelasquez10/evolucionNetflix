// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Players$LoadOwnerCoverPhotoUrisResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$OwnerCoverPhotoUrisLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Players$LoadOwnerCoverPhotoUrisResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$OwnerCoverPhotoUrisLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Players$LoadOwnerCoverPhotoUrisResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void d(final int n, final Bundle bundle) {
        bundle.setClassLoader(this.getClass().getClassLoader());
        this.De.b(new GamesClientImpl$LoadOwnerCoverPhotoUrisResultImpl(n, bundle));
    }
}
