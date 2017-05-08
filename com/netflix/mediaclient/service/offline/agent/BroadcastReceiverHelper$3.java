// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackType;
import com.netflix.mediaclient.util.IntentUtils;
import android.os.Looper;
import android.os.Handler;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class BroadcastReceiverHelper$3 extends BroadcastReceiver
{
    final /* synthetic */ BroadcastReceiverHelper this$0;
    
    BroadcastReceiverHelper$3(final BroadcastReceiverHelper this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.onUserAgentIntentReceived(intent);
    }
}
