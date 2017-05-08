// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import java.util.concurrent.ThreadFactory;
import android.os.Process;

class PriorityThreadFactory$1 implements Runnable
{
    final /* synthetic */ PriorityThreadFactory this$0;
    final /* synthetic */ Runnable val$runnable;
    
    PriorityThreadFactory$1(final PriorityThreadFactory this$0, final Runnable val$runnable) {
        this.this$0 = this$0;
        this.val$runnable = val$runnable;
    }
    
    @Override
    public void run() {
        while (true) {
            try {
                Process.setThreadPriority(this.this$0.mThreadPriority);
                this.val$runnable.run();
            }
            catch (Throwable t) {
                continue;
            }
            break;
        }
    }
}
