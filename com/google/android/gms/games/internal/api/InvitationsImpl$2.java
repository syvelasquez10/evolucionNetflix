// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.common.api.BaseImplementation$b;
import com.google.android.gms.games.internal.GamesClientImpl;
import com.google.android.gms.common.api.Api$a;

class InvitationsImpl$2 extends InvitationsImpl$LoadInvitationsImpl
{
    final /* synthetic */ String XX;
    final /* synthetic */ int Yu;
    
    @Override
    protected void a(final GamesClientImpl gamesClientImpl) {
        gamesClientImpl.e((BaseImplementation$b<Invitations$LoadInvitationsResult>)this, this.XX, this.Yu);
    }
}
