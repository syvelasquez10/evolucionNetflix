// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.support.v4.media.session.MediaSessionCompat$Callback;

class PlayerFragment$1 extends MediaSessionCompat$Callback
{
    final /* synthetic */ PlayerFragment this$0;
    
    PlayerFragment$1(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onPause() {
        Log.d("PlayerFragment", "mediaSession onPause");
        this.this$0.doPause();
    }
    
    @Override
    public void onPlay() {
        Log.d("PlayerFragment", "mediaSession  onPlay");
        this.this$0.doUnpause();
    }
}
