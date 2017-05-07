// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$P2PConnectedCallback extends d$b<RoomStatusUpdateListener>
{
    final /* synthetic */ GamesClientImpl Wr;
    private final String Xg;
    
    GamesClientImpl$P2PConnectedCallback(final GamesClientImpl wr, final RoomStatusUpdateListener roomStatusUpdateListener, final String xg) {
        this.Wr = wr;
        super(roomStatusUpdateListener);
        this.Xg = xg;
    }
    
    public void a(final RoomStatusUpdateListener roomStatusUpdateListener) {
        if (roomStatusUpdateListener != null) {
            roomStatusUpdateListener.onP2PConnected(this.Xg);
        }
    }
    
    @Override
    protected void gT() {
    }
}
