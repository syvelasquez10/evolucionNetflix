// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$InvitationRemovedCallback extends d$b<OnInvitationReceivedListener>
{
    private final String WD;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$InvitationRemovedCallback(final GamesClientImpl wr, final OnInvitationReceivedListener onInvitationReceivedListener, final String wd) {
        this.Wr = wr;
        super(onInvitationReceivedListener);
        this.WD = wd;
    }
    
    protected void b(final OnInvitationReceivedListener onInvitationReceivedListener) {
        onInvitationReceivedListener.onInvitationRemoved(this.WD);
    }
    
    @Override
    protected void gT() {
    }
}
