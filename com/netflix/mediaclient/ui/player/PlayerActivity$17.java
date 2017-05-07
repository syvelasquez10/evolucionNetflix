// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PlayerActivity$17 implements Runnable
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$17(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("PlayerActivity", "Playback canceled when not longer on WiFi");
        this.this$0.cleanupAndExit();
    }
}
