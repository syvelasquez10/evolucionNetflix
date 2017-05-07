// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.os.Process;
import com.netflix.mediaclient.Log;

class PlayerActivity$20 implements Runnable
{
    final /* synthetic */ PlayerActivity this$0;
    
    PlayerActivity$20(final PlayerActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("PlayerActivity", "===fatal error, shutdown===");
        final int myPid = Process.myPid();
        Log.d("PlayerActivity", "Destroying app proces " + myPid + "...");
        Process.killProcess(myPid);
        Log.d("PlayerActivity", "Destroying app proces " + myPid + " done.");
    }
}
