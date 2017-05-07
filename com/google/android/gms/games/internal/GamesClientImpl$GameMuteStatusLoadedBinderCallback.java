// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Notifications$GameMuteStatusLoadResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$GameMuteStatusLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Notifications$GameMuteStatusLoadResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$GameMuteStatusLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Notifications$GameMuteStatusLoadResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void B(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$GameMuteStatusLoadResultImpl(dataHolder));
    }
}
