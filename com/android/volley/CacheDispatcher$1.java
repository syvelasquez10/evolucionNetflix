// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import android.os.Process;
import java.util.concurrent.BlockingQueue;

class CacheDispatcher$1 implements Runnable
{
    final /* synthetic */ CacheDispatcher this$0;
    final /* synthetic */ Request val$request;
    
    CacheDispatcher$1(final CacheDispatcher this$0, final Request val$request) {
        this.this$0 = this$0;
        this.val$request = val$request;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.mNetworkQueue.put(this.val$request);
        }
        catch (InterruptedException ex) {}
    }
}
