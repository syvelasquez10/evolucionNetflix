// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Handler;
import java.util.Iterator;
import java.io.OutputStream;
import java.util.Map;
import java.io.FilterOutputStream;

class ProgressOutputStream$1 implements Runnable
{
    final /* synthetic */ ProgressOutputStream this$0;
    final /* synthetic */ RequestBatch$OnProgressCallback val$progressCallback;
    
    ProgressOutputStream$1(final ProgressOutputStream this$0, final RequestBatch$OnProgressCallback val$progressCallback) {
        this.this$0 = this$0;
        this.val$progressCallback = val$progressCallback;
    }
    
    @Override
    public void run() {
        this.val$progressCallback.onBatchProgress(this.this$0.requests, this.this$0.batchProgress, this.this$0.maxProgress);
    }
}
