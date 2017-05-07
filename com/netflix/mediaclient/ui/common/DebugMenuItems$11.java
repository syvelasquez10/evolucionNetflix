// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.Menu;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$11 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$11(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.this$0.beginTraceview();
        return true;
    }
}
