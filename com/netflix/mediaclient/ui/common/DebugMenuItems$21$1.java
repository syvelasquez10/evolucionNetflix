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
import android.widget.Toast;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.configuration.SimpleConfigurationAgentWebCallback;

class DebugMenuItems$21$1 extends SimpleConfigurationAgentWebCallback
{
    final /* synthetic */ DebugMenuItems$21 this$1;
    
    DebugMenuItems$21$1(final DebugMenuItems$21 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onConfigDataFetched(final ConfigData configData, final Status status) {
        String s = "Failed to receive config data";
        if (status.isSucces()) {
            s = s;
            if (configData != null) {
                PersistentConfig.refresh();
                s = "Config data received successfully!";
            }
        }
        Toast.makeText((Context)this.this$1.this$0.activity, (CharSequence)s, 0).show();
    }
}
