// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class TopPanel$8 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ TopPanel this$0;
    
    TopPanel$8(final TopPanel this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (this.this$0.playerFragment.isActivityValid()) {
            this.this$0.playerFragment.extendTimeoutTimer();
            this.this$0.displayMdxTargets();
        }
        return true;
    }
}
