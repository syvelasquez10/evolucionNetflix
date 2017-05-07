// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.os.Handler;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.widget.Toast;
import com.netflix.mediaclient.Log;
import android.os.Debug;

class DebugMenuItems$7 implements Runnable
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$7(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Debug.stopMethodTracing();
        Log.i(this.this$0.logTag, "Trace complete.  Get with: adb pull /sdcard/nflx.trace");
        Toast.makeText((Context)this.this$0.activity, (CharSequence)"Trace: /sdcard/nflx.trace", 1).show();
    }
}
