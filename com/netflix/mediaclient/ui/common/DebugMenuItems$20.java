// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.SubMenu;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import com.netflix.mediaclient.android.debug.DebugOverlay;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.MenuItem;
import com.netflix.mediaclient.util.net.CronetHttpURLConnectionFactory;
import android.view.MenuItem$OnMenuItemClickListener;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import android.os.Debug;

class DebugMenuItems$20 implements Runnable
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$20(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Debug.stopMethodTracing();
        Log.i(this.this$0.logTag, "**********************************************************************");
        Log.i(this.this$0.logTag, "Trace complete.  Get with: adb pull /sdcard/nflx.trace");
        Log.i(this.this$0.logTag, "**********************************************************************");
        Toast.makeText((Context)this.this$0.activity, (CharSequence)"Trace: /sdcard/nflx.trace", 1).show();
    }
}
