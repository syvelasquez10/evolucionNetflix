// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.SubMenu;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.model.leafs.OnRampEligibility$Action;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$30 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$30(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.this$0.activity.getServiceManager().doOnRampEligibilityAction(OnRampEligibility$Action.FETCH, new DebugMenuItems$30$1(this));
        return true;
    }
}
