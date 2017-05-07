// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$LeftRoomCallback extends d$b<RoomUpdateListener>
{
    private final int HF;
    private final String WF;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$LeftRoomCallback(final GamesClientImpl wr, final RoomUpdateListener roomUpdateListener, final int hf, final String wf) {
        this.Wr = wr;
        super(roomUpdateListener);
        this.HF = hf;
        this.WF = wf;
    }
    
    public void a(final RoomUpdateListener roomUpdateListener) {
        roomUpdateListener.onLeftRoom(this.HF, this.WF);
    }
    
    @Override
    protected void gT() {
    }
}
