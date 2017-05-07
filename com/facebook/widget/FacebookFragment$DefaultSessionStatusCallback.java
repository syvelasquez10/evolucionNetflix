// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.SessionState;
import com.facebook.Session;
import com.facebook.Session$StatusCallback;

class FacebookFragment$DefaultSessionStatusCallback implements Session$StatusCallback
{
    final /* synthetic */ FacebookFragment this$0;
    
    private FacebookFragment$DefaultSessionStatusCallback(final FacebookFragment this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void call(final Session session, final SessionState sessionState, final Exception ex) {
        this.this$0.onSessionStateChange(sessionState, ex);
    }
}
