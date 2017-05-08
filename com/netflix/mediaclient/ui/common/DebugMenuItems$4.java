// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$4 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    final /* synthetic */ Menu val$menu;
    
    DebugMenuItems$4(final DebugMenuItems this$0, final Menu val$menu) {
        this.this$0 = this$0;
        this.val$menu = val$menu;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (!this.this$0.requestExternalFileWritePermission()) {
            Log.e(this.this$0.logTag, "Error: Don't have External write permissions yet... ");
            return false;
        }
        CronetHttpURLConnectionFactory.getInstance((Context)this.this$0.activity).startNetLog();
        this.val$menu.removeItem(menuItem.getItemId());
        this.this$0.addStopCronetLogging(this.val$menu);
        return true;
    }
}
