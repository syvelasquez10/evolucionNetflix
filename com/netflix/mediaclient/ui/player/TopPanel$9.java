// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;

class TopPanel$9 implements DialogInterface$OnCancelListener
{
    final /* synthetic */ TopPanel this$0;
    final /* synthetic */ PlayerFragment val$controller;
    
    TopPanel$9(final TopPanel this$0, final PlayerFragment val$controller) {
        this.this$0 = this$0;
        this.val$controller = val$controller;
    }
    
    public void onCancel(final DialogInterface dialogInterface) {
        Log.d("screen", "Mdx::onCancel");
        this.val$controller.doUnpause();
    }
}
