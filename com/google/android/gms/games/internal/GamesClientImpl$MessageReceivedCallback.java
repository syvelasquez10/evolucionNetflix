// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$MessageReceivedCallback extends d$b<RealTimeMessageReceivedListener>
{
    private final RealTimeMessage WZ;
    final /* synthetic */ GamesClientImpl Wr;
    
    GamesClientImpl$MessageReceivedCallback(final GamesClientImpl wr, final RealTimeMessageReceivedListener realTimeMessageReceivedListener, final RealTimeMessage wz) {
        this.Wr = wr;
        super(realTimeMessageReceivedListener);
        this.WZ = wz;
    }
    
    public void a(final RealTimeMessageReceivedListener realTimeMessageReceivedListener) {
        if (realTimeMessageReceivedListener != null) {
            realTimeMessageReceivedListener.onRealTimeMessageReceived(this.WZ);
        }
    }
    
    @Override
    protected void gT() {
    }
}
