// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.log;

import com.netflix.mediaclient.util.AndroidUtils;
import android.app.ActivityManager$MemoryInfo;
import android.app.ActivityManager;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executors;
import android.content.Context;
import com.netflix.mediaclient.Log;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

class MemoryManager$1 implements Runnable
{
    final /* synthetic */ MemoryManager this$0;
    
    MemoryManager$1(final MemoryManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.cancelHelper.cancel(true);
        this.this$0.cancelHelper = null;
        this.this$0.executor.shutdown();
        this.this$0.executor = null;
        Log.d("nf-memory", "Memory manager stoped");
    }
}
