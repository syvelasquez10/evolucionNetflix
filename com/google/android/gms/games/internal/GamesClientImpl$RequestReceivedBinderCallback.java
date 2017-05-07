// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.internal.d$b;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.request.OnRequestReceivedListener;

final class GamesClientImpl$RequestReceivedBinderCallback extends AbstractGamesCallbacks
{
    final /* synthetic */ GamesClientImpl Wr;
    private final OnRequestReceivedListener Xp;
    
    GamesClientImpl$RequestReceivedBinderCallback(final GamesClientImpl wr, final OnRequestReceivedListener xp) {
        this.Wr = wr;
        this.Xp = xp;
    }
    
    @Override
    public void o(final DataHolder dataHolder) {
        final GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
        GameRequest gameRequest = null;
        try {
            if (gameRequestBuffer.getCount() > 0) {
                gameRequest = gameRequestBuffer.get(0).freeze();
            }
            gameRequestBuffer.release();
            if (gameRequest != null) {
                this.Wr.a(new GamesClientImpl$RequestReceivedCallback(this.Wr, this.Xp, gameRequest));
            }
        }
        finally {
            gameRequestBuffer.release();
        }
    }
    
    @Override
    public void onRequestRemoved(final String s) {
        this.Wr.a(new GamesClientImpl$RequestRemovedCallback(this.Wr, this.Xp, s));
    }
}
