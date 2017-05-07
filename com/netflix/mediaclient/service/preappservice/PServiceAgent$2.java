// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ThreadUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.CommonStatus;
import android.os.Handler;
import com.netflix.mediaclient.android.app.Status;

class PServiceAgent$2 implements Runnable
{
    final /* synthetic */ PServiceAgent this$0;
    
    PServiceAgent$2(final PServiceAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.initCallback.onInitComplete(this.this$0, this.this$0.initErrorResult);
    }
}
