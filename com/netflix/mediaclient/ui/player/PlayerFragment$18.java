// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.os.Process;
import com.netflix.mediaclient.Log;

class PlayerFragment$18 implements Runnable
{
    final /* synthetic */ PlayerFragment this$0;
    
    PlayerFragment$18(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("PlayerFragment", "===fatal error, shutdown===");
        final int myPid = Process.myPid();
        Log.d("PlayerFragment", "Destroying app proces " + myPid + "...");
        Process.killProcess(myPid);
        Log.d("PlayerFragment", "Destroying app proces " + myPid + " done.");
    }
}
