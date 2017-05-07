// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.View;
import android.view.View$OnClickListener;

class BottomPanel$1 implements View$OnClickListener
{
    final /* synthetic */ BottomPanel this$0;
    
    BottomPanel$1(final BottomPanel this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        final PlayerActivity context = this.this$0.context;
        if (context != null) {
            context.extendTimeoutTimer();
        }
        this.this$0.displayMdxTargets();
    }
}
