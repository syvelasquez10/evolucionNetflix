// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.content.IntentFilter;
import android.content.Context;
import android.content.BroadcastReceiver;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.Session$StatusCallback;

class SessionTracker$CallbackWrapper implements Session$StatusCallback
{
    final /* synthetic */ SessionTracker this$0;
    private final Session$StatusCallback wrapped;
    
    public SessionTracker$CallbackWrapper(final SessionTracker this$0, final Session$StatusCallback wrapped) {
        this.this$0 = this$0;
        this.wrapped = wrapped;
    }
    
    @Override
    public void call(final Session session, final SessionState sessionState, final Exception ex) {
        if (this.wrapped != null && this.this$0.isTracking()) {
            this.wrapped.call(session, sessionState, ex);
        }
        if (session == this.this$0.session && sessionState.isClosed()) {
            this.this$0.setSession(null);
        }
    }
}
