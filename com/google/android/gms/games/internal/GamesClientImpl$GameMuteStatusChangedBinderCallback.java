// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.Notifications$GameMuteStatusChangeResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$GameMuteStatusChangedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Notifications$GameMuteStatusChangeResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$GameMuteStatusChangedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Notifications$GameMuteStatusChangeResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void a(final int n, final String s, final boolean b) {
        this.De.b(new GamesClientImpl$GameMuteStatusChangeResultImpl(n, s, b));
    }
}
