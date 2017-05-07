// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.javabridge.ui.IMedia;
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
            player$PlayerListener.onPrepared();
            final IMedia media = this.this$0.mPlayer.getNrdController().getNrdp().getMedia();
            player$PlayerListener.onVideoSizeChanged(media.getVideoWidth(), media.getVideoHeight());
        }
    }
}
