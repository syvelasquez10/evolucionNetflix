// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.games.Games$BaseGamesApiMethodImpl;

abstract class InvitationsImpl$LoadInvitationsImpl extends Games$BaseGamesApiMethodImpl<Invitations$LoadInvitationsResult>
{
    public Invitations$LoadInvitationsResult T(final Status status) {
        return new InvitationsImpl$LoadInvitationsImpl$1(this, status);
    }
}
