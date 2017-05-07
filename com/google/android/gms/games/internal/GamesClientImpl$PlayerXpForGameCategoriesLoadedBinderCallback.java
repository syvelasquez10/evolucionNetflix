// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import android.os.Bundle;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Players$LoadXpForGameCategoriesResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$PlayerXpForGameCategoriesLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Players$LoadXpForGameCategoriesResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$PlayerXpForGameCategoriesLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Players$LoadXpForGameCategoriesResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void e(final int n, final Bundle bundle) {
        bundle.setClassLoader(this.getClass().getClassLoader());
        this.De.b(new GamesClientImpl$LoadXpForGameCategoriesResultImpl(new Status(n), bundle));
    }
}
