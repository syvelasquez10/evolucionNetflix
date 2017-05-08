// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class HomeActivity$1 extends BroadcastReceiver
{
    final /* synthetic */ HomeActivity this$0;
    
    HomeActivity$1(final HomeActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        this.this$0.invalidateOptionsMenu();
    }
}
