// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class MDXControllerActivity$FinishReceiver extends BroadcastReceiver
{
    final /* synthetic */ MDXControllerActivity this$0;
    
    MDXControllerActivity$FinishReceiver(final MDXControllerActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (intent.getAction() == "com.netflix.mediaclient.ui.player.MDXControllerActivity.ACTION_FINISH") {
            this.this$0.setResult(0);
            this.this$0.finish();
        }
    }
}
