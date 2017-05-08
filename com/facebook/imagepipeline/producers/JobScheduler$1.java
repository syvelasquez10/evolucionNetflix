// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.EncodedImage;

class JobScheduler$1 implements Runnable
{
    final /* synthetic */ JobScheduler this$0;
    
    JobScheduler$1(final JobScheduler this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.doJob();
    }
}
