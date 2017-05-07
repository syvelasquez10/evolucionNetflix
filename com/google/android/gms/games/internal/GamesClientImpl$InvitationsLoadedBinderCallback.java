// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.n;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.common.api.BaseImplementation$b;

final class GamesClientImpl$InvitationsLoadedBinderCallback extends AbstractGamesCallbacks
{
    private final BaseImplementation$b<Invitations$LoadInvitationsResult> De;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$InvitationsLoadedBinderCallback(final GamesClientImpl wr, final BaseImplementation$b<Invitations$LoadInvitationsResult> baseImplementation$b) {
        this.Wr = wr;
        this.De = n.b(baseImplementation$b, "Holder must not be null");
    }
    
    @Override
    public void m(final DataHolder dataHolder) {
        this.De.b(new GamesClientImpl$LoadInvitationsResultImpl(dataHolder));
    }
}
