// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer$ReliableMessageSentCallback;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$RealTimeMessageSentCallback extends d$b<RealTimeMultiplayer$ReliableMessageSentCallback>
{
    private final int HF;
    final /* synthetic */ GamesClientImpl Wr;
    private final String Xm;
    private final int Xn;
    
    GamesClientImpl$RealTimeMessageSentCallback(final GamesClientImpl wr, final RealTimeMultiplayer$ReliableMessageSentCallback realTimeMultiplayer$ReliableMessageSentCallback, final int hf, final int xn, final String xm) {
        this.Wr = wr;
        super(realTimeMultiplayer$ReliableMessageSentCallback);
        this.HF = hf;
        this.Xn = xn;
        this.Xm = xm;
    }
    
    public void a(final RealTimeMultiplayer$ReliableMessageSentCallback realTimeMultiplayer$ReliableMessageSentCallback) {
        if (realTimeMultiplayer$ReliableMessageSentCallback != null) {
            realTimeMultiplayer$ReliableMessageSentCallback.onRealTimeMessageSent(this.HF, this.Xn, this.Xm);
        }
    }
    
    @Override
    protected void gT() {
    }
}
