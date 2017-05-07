// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal.api;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.multiplayer.Invitations$LoadInvitationsResult;

class InvitationsImpl$LoadInvitationsImpl$1 implements Invitations$LoadInvitationsResult
{
    final /* synthetic */ Status CW;
    final /* synthetic */ InvitationsImpl$LoadInvitationsImpl Yx;
    
    InvitationsImpl$LoadInvitationsImpl$1(final InvitationsImpl$LoadInvitationsImpl yx, final Status cw) {
        this.Yx = yx;
        this.CW = cw;
    }
    
    @Override
    public InvitationBuffer getInvitations() {
        return new InvitationBuffer(DataHolder.as(14));
    }
    
    @Override
    public Status getStatus() {
        return this.CW;
    }
    
    @Override
    public void release() {
    }
}
