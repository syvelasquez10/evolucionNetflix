// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import java.util.Map;

class PServiceFetchAgent$2$1 implements Runnable
{
    final /* synthetic */ PServiceFetchAgent$2 this$1;
    final /* synthetic */ PDiskData val$newData;
    
    PServiceFetchAgent$2$1(final PServiceFetchAgent$2 this$1, final PDiskData val$newData) {
        this.this$1 = this$1;
        this.val$newData = val$newData;
    }
    
    @Override
    public void run() {
        this.this$1.this$0.setLoadFromDiskInProgress(false);
        if (this.val$newData != null) {
            this.this$1.this$0.mDiskData = this.val$newData;
        }
        if (this.this$1.val$intent != null) {
            this.this$1.this$0.notifyOthers(this.this$1.val$intent);
        }
    }
}
