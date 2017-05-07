// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PlayerActivity$13 implements Runnable
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$13(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.doPause();
        Log.d("PlayerActivity", "onWindowFocusChanged done");
    }
}
