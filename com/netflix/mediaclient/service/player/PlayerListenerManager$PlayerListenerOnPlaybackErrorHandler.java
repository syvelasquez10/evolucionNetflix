// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackError;
import com.netflix.mediaclient.service.player.exoplayback.ExoPlaybackError;
import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;

class PlayerListenerManager$PlayerListenerOnPlaybackErrorHandler implements PlayerListenerManager$PlayerListenerHandler
{
    final /* synthetic */ PlayerListenerManager this$0;
    
    private PlayerListenerManager$PlayerListenerOnPlaybackErrorHandler(final PlayerListenerManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void handle(final IPlayer$PlayerListener player$PlayerListener, final Object... array) {
        if (player$PlayerListener.isListening() && array != null && array.length >= 1 && array[0] instanceof ExoPlaybackError) {
            player$PlayerListener.onPlaybackError((IPlayer$PlaybackError)array[0]);
        }
    }
}
