// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;
import com.google.android.gms.common.api.a;

final class GamesClientImpl$LoadInvitationsResultImpl extends a implements Invitations$LoadInvitationsResult
{
    private final InvitationBuffer WL;
    
    GamesClientImpl$LoadInvitationsResultImpl(final DataHolder dataHolder) {
        super(dataHolder);
        this.WL = new InvitationBuffer(dataHolder);
    }
    
    @Override
    public InvitationBuffer getInvitations() {
        return this.WL;
    }
}
