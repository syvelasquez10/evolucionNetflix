// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.settings;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class SettingsActivity$1 extends BroadcastReceiver
{
    final /* synthetic */ SettingsActivity this$0;
    
    SettingsActivity$1(final SettingsActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        Log.i("nf_settings", "mOsvSpaceUpdatedReceiver");
        this.this$0.refreshStorageIndicator();
    }
}
