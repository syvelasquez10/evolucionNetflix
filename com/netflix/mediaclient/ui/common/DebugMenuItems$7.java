// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Handler;
import android.os.Debug;
import android.app.Activity;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.Menu;
import android.content.Context;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$7 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$7(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Log.d(this.this$0.logTag, "Making refreshCw() call");
        this.this$0.activity.getServiceManager().getBrowse().refreshCw();
        return true;
    }
}
