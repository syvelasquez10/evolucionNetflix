// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.content.IntentFilter;
import com.facebook.Session$StatusCallback;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.Session;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class SessionTracker$ActiveSessionBroadcastReceiver extends BroadcastReceiver
{
    final /* synthetic */ SessionTracker this$0;
    
    private SessionTracker$ActiveSessionBroadcastReceiver(final SessionTracker this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if ("com.facebook.sdk.ACTIVE_SESSION_SET".equals(intent.getAction())) {
            final Session activeSession = Session.getActiveSession();
            if (activeSession != null) {
                activeSession.addCallback(this.this$0.callback);
            }
        }
    }
}
