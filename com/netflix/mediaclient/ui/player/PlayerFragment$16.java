// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PlayerFragment$16 implements Runnable
{
    final /* synthetic */ PlayerFragment this$0;
    
    PlayerFragment$16(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("PlayerFragment", "pause has timed out, exit playback");
        this.this$0.cleanupAndExit();
    }
}
