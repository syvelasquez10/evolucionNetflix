// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.Intent;

class PServiceFetchAgent$1$1 implements Runnable
{
    final /* synthetic */ PServiceFetchAgent$1 this$1;
    
    PServiceFetchAgent$1$1(final PServiceFetchAgent$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.mLoadFromDiskInProgress = false;
        this.this$1.this$0.notifyOthers(this.this$1.val$intent);
    }
}
