// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;

class StandardSlidingMenu$12$1 implements Runnable
{
    final /* synthetic */ StandardSlidingMenu$12 this$0;
    final /* synthetic */ AccountHandler val$handler;
    final /* synthetic */ Status val$timeoutStatus;
    
    StandardSlidingMenu$12$1(final StandardSlidingMenu$12 this$0, final AccountHandler val$handler, final Status val$timeoutStatus) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
        this.val$timeoutStatus = val$timeoutStatus;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(null, this.val$timeoutStatus);
    }
}
