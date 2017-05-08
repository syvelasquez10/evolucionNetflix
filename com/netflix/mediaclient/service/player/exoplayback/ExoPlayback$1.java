// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.servicemgr.IPlayer$PlayerListener;
import com.netflix.mediaclient.service.player.PlayerListenerManager$PlayerListenerHandler;

class ExoPlayback$1 implements Runnable
{
    final /* synthetic */ ExoPlayback this$0;
    final /* synthetic */ Object[] val$arguments;
    final /* synthetic */ PlayerListenerManager$PlayerListenerHandler val$handler;
    final /* synthetic */ IPlayer$PlayerListener val$listener;
    
    ExoPlayback$1(final ExoPlayback this$0, final PlayerListenerManager$PlayerListenerHandler val$handler, final IPlayer$PlayerListener val$listener, final Object[] val$arguments) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
        this.val$listener = val$listener;
        this.val$arguments = val$arguments;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(this.val$listener, this.val$arguments);
    }
}
