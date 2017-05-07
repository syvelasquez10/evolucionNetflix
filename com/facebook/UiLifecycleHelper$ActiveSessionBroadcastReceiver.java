// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class UiLifecycleHelper$ActiveSessionBroadcastReceiver extends BroadcastReceiver
{
    final /* synthetic */ UiLifecycleHelper this$0;
    
    private UiLifecycleHelper$ActiveSessionBroadcastReceiver(final UiLifecycleHelper this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if ("com.facebook.sdk.ACTIVE_SESSION_SET".equals(intent.getAction())) {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null && this.this$0.callback != null) {
                activeSession.addCallback(this.this$0.callback);
            }
        }
        else if ("com.facebook.sdk.ACTIVE_SESSION_UNSET".equals(intent.getAction())) {
            final Session activeSession2 = Session.getActiveSession();
            if (activeSession2 != null && this.this$0.callback != null) {
                activeSession2.removeCallback(this.this$0.callback);
            }
        }
    }
}
