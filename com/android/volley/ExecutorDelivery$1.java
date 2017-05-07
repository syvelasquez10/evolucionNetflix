// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.os.Handler;
import java.util.concurrent.Executor;

class ExecutorDelivery$1 implements Executor
{
    final /* synthetic */ ExecutorDelivery this$0;
    final /* synthetic */ Handler val$handler;
    
    ExecutorDelivery$1(final ExecutorDelivery this$0, final Handler val$handler) {
        this.this$0 = this$0;
        this.val$handler = val$handler;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        this.val$handler.post(runnable);
    }
}
