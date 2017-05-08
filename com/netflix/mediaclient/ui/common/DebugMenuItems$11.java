// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.content.Context;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$11 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$11(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        Log.d(this.this$0.logTag, "Sending home refresh: com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO");
        this.this$0.activity.sendBroadcast(new Intent("com.netflix.mediaclient.intent.action.REFRESH_HOME_LOLOMO"));
        return true;
    }
}
