// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$InvitationReceivedCallback extends d$b<OnInvitationReceivedListener>
{
    private final Invitation WC;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$InvitationReceivedCallback(final GamesClientImpl wr, final OnInvitationReceivedListener onInvitationReceivedListener, final Invitation wc) {
        this.Wr = wr;
        super(onInvitationReceivedListener);
        this.WC = wc;
    }
    
    protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
        onInvitationReceivedListener.onInvitationReceived(this.WC);
    }
    
    @Override
    protected void gT() {
    }
}
