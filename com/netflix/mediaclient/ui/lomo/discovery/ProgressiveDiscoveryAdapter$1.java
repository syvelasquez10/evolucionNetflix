// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class ProgressiveDiscoveryAdapter$1 extends BroadcastReceiver
{
    final /* synthetic */ ProgressiveDiscoveryAdapter this$0;
    
    ProgressiveDiscoveryAdapter$1(final ProgressiveDiscoveryAdapter this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.fetchMoreData(0, 6);
    }
}
