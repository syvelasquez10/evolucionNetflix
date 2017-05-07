// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import android.view.View$OnClickListener;

class TopPanel$1 implements View$OnClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$1(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.context.performUpAction();
        this.this$0.context.cleanupAndExit();
    }
}
