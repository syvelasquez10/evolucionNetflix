// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.content.Intent;
import android.net.Uri;
import com.netflix.mediaclient.Log;

class PlayerErrorDialogDescriptorFactory$2$1 implements Runnable
{
    final /* synthetic */ PlayerErrorDialogDescriptorFactory$2 this$0;
    final /* synthetic */ String val$urlLink;
    
    PlayerErrorDialogDescriptorFactory$2$1(final PlayerErrorDialogDescriptorFactory$2 this$0, final String val$urlLink) {
        this.this$0 = this$0;
        this.val$urlLink = val$urlLink;
    }
    
    @Override
    public void run() {
        if (Log.isLoggable()) {
            Log.d("ErrorManager", "Open internal web view to " + this.val$urlLink);
        }
        this.this$0.val$fragment.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.val$urlLink)));
        Log.d("ErrorManager", "Exit from playback after browser is started");
        this.this$0.val$fragment.finish();
    }
}
