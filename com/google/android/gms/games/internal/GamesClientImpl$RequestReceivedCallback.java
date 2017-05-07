// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$RequestReceivedCallback extends d$b<OnRequestReceivedListener>
{
    final /* synthetic */ GamesClientImpl Wr;
    private final GameRequest Xq;
    
    GamesClientImpl$RequestReceivedCallback(final GamesClientImpl wr, final OnRequestReceivedListener onRequestReceivedListener, final GameRequest xq) {
        this.Wr = wr;
        super(onRequestReceivedListener);
        this.Xq = xq;
    }
    
    protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
        onRequestReceivedListener.onRequestReceived(this.Xq);
    }
    
    @Override
    protected void gT() {
    }
}
