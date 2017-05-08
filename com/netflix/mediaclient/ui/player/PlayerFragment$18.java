// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class PlayerFragment$18 extends BroadcastReceiver
{
    final /* synthetic */ PlayerFragment this$0;
    
    PlayerFragment$18(final PlayerFragment this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.handleConnectivityCheck();
    }
}
