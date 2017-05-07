// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;

final class GamesClientImpl$InvitationReceivedBinderCallback extends AbstractGamesCallbacks
{
    private final OnInvitationReceivedListener WB;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$InvitationReceivedBinderCallback(final GamesClientImpl wr, final OnInvitationReceivedListener wb) {
        this.Wr = wr;
        this.WB = wb;
    }
    
    @Override
    public void n(final DataHolder dataHolder) {
        final InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
        Invitation invitation = null;
        try {
            if (invitationBuffer.getCount() > 0) {
                invitation = invitationBuffer.get(0).freeze();
            }
            invitationBuffer.release();
            if (invitation != null) {
                this.Wr.a(new GamesClientImpl$InvitationReceivedCallback(this.Wr, this.WB, invitation));
            }
        }
        finally {
            invitationBuffer.release();
        }
    }
    
    @Override
    public void onInvitationRemoved(final String s) {
        this.Wr.a(new GamesClientImpl$InvitationRemovedCallback(this.Wr, this.WB, s));
    }
}
