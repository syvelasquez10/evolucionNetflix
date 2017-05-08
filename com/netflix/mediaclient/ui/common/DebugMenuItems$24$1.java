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
import android.view.MenuItem$OnMenuItemClickListener;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import android.content.Intent;
import com.netflix.mediaclient.ui.signup.OnRampActivity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.leafs.OnRampEligibility;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class DebugMenuItems$24$1 extends SimpleManagerCallback
{
    final /* synthetic */ DebugMenuItems$24 this$1;
    
    DebugMenuItems$24$1(final DebugMenuItems$24 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onOnRampEligibilityAction(final OnRampEligibility onRampEligibility, final Status status) {
        if (status.isSucces()) {
            this.this$1.this$0.activity.startActivity(new Intent((Context)this.this$1.this$0.activity, (Class)OnRampActivity.class));
        }
    }
}
