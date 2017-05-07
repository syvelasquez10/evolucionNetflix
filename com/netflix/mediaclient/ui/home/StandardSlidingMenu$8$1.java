// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.home;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;

class StandardSlidingMenu$8$1 implements Runnable
{
    final /* synthetic */ StandardSlidingMenu$8 this$0;
    final /* synthetic */ AccountHandler val$handler;
    
    StandardSlidingMenu$8$1(final StandardSlidingMenu$8 this$0, final AccountHandler val$handler) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
    }
    
    @Override
    public void run() {
        this.val$handler.handle(null, CommonStatus.NETWORK_ERROR);
    }
}
