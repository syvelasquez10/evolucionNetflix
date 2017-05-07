// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class StandardSlidingMenu$8$2 extends SimpleManagerCallback
{
    final /* synthetic */ StandardSlidingMenu$8 this$0;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Runnable val$timeout;
    
    StandardSlidingMenu$8$2(final StandardSlidingMenu$8 this$0, final Runnable val$timeout, final AccountHandler val$handler) {
        this.this$0 = this$0;
        this.val$timeout = val$timeout;
        this.val$handler = val$handler;
    }
    
    @Override
    public void onAutoLoginTokenCreated(final String s, final Status status) {
        this.this$0.val$context.getHandler().removeCallbacks(this.val$timeout);
        this.val$handler.handle(s, status);
    }
}
