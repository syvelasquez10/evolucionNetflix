// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.Log;
import android.app.Activity;
import com.netflix.mediaclient.util.AndroidUtils;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class HomeActivity$2 extends BroadcastReceiver
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$2(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (AndroidUtils.isActivityFinishedOrDestroyed(this.this$0)) {
            return;
        }
        if (intent == null || !"com.netflix.mediaclient.service.ACTION_EXPAND_HOME_MDX_MINI_PLAYER".equals(intent.getAction())) {
            Log.d("HomeActivity", "Invalid intent: ", intent);
            return;
        }
        this.this$0.notifyMdxMiniPlayerShown(true);
    }
}
