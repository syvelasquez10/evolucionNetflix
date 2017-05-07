// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player;

import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

class PlayParamsReceiver$1 implements Runnable
{
    final /* synthetic */ PlayParamsReceiver this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ Intent val$intent;
    
    PlayParamsReceiver$1(final PlayParamsReceiver this$0, final Context val$context, final Intent val$intent) {
        this.this$0 = this$0;
        this.val$context = val$context;
        this.val$intent = val$intent;
    }
    
    @Override
    public void run() {
        this.this$0.handleTweakBandwith(this.val$context, this.val$intent);
    }
}
