// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.common.internal.d$b;

final class GamesClientImpl$RequestRemovedCallback extends d$b<OnRequestReceivedListener>
{
    final /* synthetic */ GamesClientImpl Wr;
    private final String Xr;
    
    GamesClientImpl$RequestRemovedCallback(final GamesClientImpl wr, final OnRequestReceivedListener onRequestReceivedListener, final String xr) {
        this.Wr = wr;
        super(onRequestReceivedListener);
        this.Xr = xr;
    }
    
    protected void b(final OnRequestReceivedListener onRequestReceivedListener) {
        onRequestReceivedListener.onRequestRemoved(this.Xr);
    }
    
    @Override
    protected void gT() {
    }
}
