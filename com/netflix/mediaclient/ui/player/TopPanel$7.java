// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$7 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$7(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        final PlayerActivity context = this.this$0.context;
        if (context != null) {
            context.extendTimeoutTimer();
        }
        this.this$0.displayMdxTargets();
        return true;
    }
}
