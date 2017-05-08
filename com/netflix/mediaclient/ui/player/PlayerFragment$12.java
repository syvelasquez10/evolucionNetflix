// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;

class PlayerFragment$12 implements Runnable
{
    final /* synthetic */ PlayerFragment this$0;
    
    PlayerFragment$12(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.doPause();
        Log.d("PlayerFragment", "onWindowFocusChanged done");
    }
}
