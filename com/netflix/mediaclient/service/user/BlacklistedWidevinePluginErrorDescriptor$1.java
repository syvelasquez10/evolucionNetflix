// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;

class BlacklistedWidevinePluginErrorDescriptor$1 implements Runnable
{
    final /* synthetic */ BlacklistedWidevinePluginErrorDescriptor this$0;
    final /* synthetic */ Context val$context;
    
    BlacklistedWidevinePluginErrorDescriptor$1(final BlacklistedWidevinePluginErrorDescriptor this$0, final Context val$context) {
        this.this$0 = this$0;
        this.val$context = val$context;
    }
    
    @Override
    public void run() {
        Log.d("nf_user_error", "Blacklisted Widevine L3 plugin, fail back to legacy crypto scheme ");
        PreferenceUtils.putBooleanPref(this.val$context, "nf_disable_widevine_l3_v3", true);
        AndroidUtils.forceStop(this.val$context);
    }
}
