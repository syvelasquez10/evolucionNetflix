// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.content.Context;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$5 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$5(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        if (!this.this$0.requestExternalFileWritePermission()) {
            Log.e(this.this$0.logTag, "Error: Don't have External write permissions yet... ");
            return false;
        }
        final ServiceManager serviceManager = this.this$0.activity.getServiceManager();
        if (serviceManager != null) {
            serviceManager.getBrowse().dumpCacheToDisk();
        }
        return true;
    }
}
