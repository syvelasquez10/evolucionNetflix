// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.media.Watermark;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;

class PlayerListenerManager$PlayerListenerPrepareHandler implements PlayerListenerManager$PlayerListenerHandler
{
    final /* synthetic */ PlayerListenerManager this$0;
    
    private PlayerListenerManager$PlayerListenerPrepareHandler(final PlayerListenerManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void handle(final IPlayer$PlayerListener player$PlayerListener, final Object... array) {
        if (player$PlayerListener.isListening()) {
            if (array != null && array.length >= 1 && array[0] instanceof Watermark) {
                player$PlayerListener.onPrepared((Watermark)array[0]);
                return;
            }
            player$PlayerListener.onPrepared(null);
        }
    }
}
