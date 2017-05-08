// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.support.v4.app.ActivityCompat;
import com.netflix.mediaclient.util.PermissionUtils;
import android.os.Handler;
import android.os.Debug;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.home.HomeActivity;
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Context;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;

class DebugMenuItems$1$1 implements DialogInterface$OnClickListener
{
    final /* synthetic */ DebugMenuItems$1 this$1;
    final /* synthetic */ boolean val$isTestEnvironment;
    
    DebugMenuItems$1$1(final DebugMenuItems$1 this$1, final boolean val$isTestEnvironment) {
        this.this$1 = this$1;
        this.val$isTestEnvironment = val$isTestEnvironment;
    }
    
    public void onClick(final DialogInterface dialogInterface, final int n) {
        String s;
        if (this.val$isTestEnvironment) {
            if (n == 0) {
                s = "api-int.test.netflix.com";
            }
            else {
                s = "api.test.netflix.com";
            }
        }
        else if (n == 0) {
            s = "api-global.netflix.com";
        }
        else {
            s = "api-staging.netflix.com";
        }
        PreferenceUtils.putStringPref((Context)this.this$1.this$0.activity, "api_environment_preference", s);
        AndroidUtils.restartApplication(this.this$1.this$0.activity);
    }
}
