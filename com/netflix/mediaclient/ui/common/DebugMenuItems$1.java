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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.android.activity.KitchensinkActivity;
import android.view.MenuItem;
import android.view.MenuItem$OnMenuItemClickListener;

class DebugMenuItems$1 implements MenuItem$OnMenuItemClickListener
{
    final /* synthetic */ DebugMenuItems this$0;
    
    DebugMenuItems$1(final DebugMenuItems this$0) {
        this.this$0 = this$0;
    }
    
    public boolean onMenuItemClick(final MenuItem menuItem) {
        this.this$0.activity.startActivity(new Intent((Context)this.this$0.activity, (Class)KitchensinkActivity.class));
        return true;
    }
}
