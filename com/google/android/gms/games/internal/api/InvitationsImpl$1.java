// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class InvitationsImpl$1 extends InvitationsImpl$LoadInvitationsImpl
{
    final /* synthetic */ int Yu;
    final /* synthetic */ InvitationsImpl Yv;
    
    InvitationsImpl$1(final InvitationsImpl yv, final int yu) {
        this.Yv = yv;
        this.Yu = yu;
        super((InvitationsImpl$1)null);
    }
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.c((BaseImplementation$b<Invitations$LoadInvitationsResult>)this, this.Yu);
    }
}
