// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PlayerActivity$16 implements Runnable
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$16(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("PlayerActivity", "pause has timed out, exit playback");
        this.this$0.cleanupAndExit();
    }
}
