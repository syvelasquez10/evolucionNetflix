// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.network;

import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.GuardedAsyncTask;

class ForwardingCookieHandler$4 extends GuardedAsyncTask<Void, Void>
{
    final /* synthetic */ ForwardingCookieHandler this$0;
    final /* synthetic */ Runnable val$runnable;
    
    ForwardingCookieHandler$4(final ForwardingCookieHandler this$0, final ReactContext reactContext, final Runnable val$runnable) {
        this.this$0 = this$0;
        this.val$runnable = val$runnable;
        super(reactContext);
    }
    
    @Override
    protected void doInBackgroundGuarded(final Void... array) {
        this.val$runnable.run();
    }
}
