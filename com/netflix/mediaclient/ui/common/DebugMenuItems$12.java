// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.content.BroadcastReceiver;
import android.view.MenuItem;
import android.content.Context;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$12 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ Context val$context;
    
    DebugMenuItems$12(final DebugMenuItems this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.val$context.unregisterReceiver((BroadcastReceiver)null);
        return true;
    }
}
