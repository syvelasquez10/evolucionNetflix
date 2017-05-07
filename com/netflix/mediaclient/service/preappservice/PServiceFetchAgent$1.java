// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.BackgroundTask;
import android.content.Intent;

class PServiceFetchAgent$1 extends PDiskDataRepository$LoadCallback
{
    final /* synthetic */ PServiceFetchAgent this$0;
    final /* synthetic */ Intent val$intent;
    
    PServiceFetchAgent$1(final PServiceFetchAgent this$0, final Intent val$intent) {
        this.this$0 = this$0;
        this.val$intent = val$intent;
    }
    
    @Override
    public void onDataLoaded(final PDiskData pDiskData) {
        if (pDiskData == null) {
            this.this$0.loadDefaultData();
        }
        else {
            this.this$0.mDiskData = pDiskData;
        }
        this.this$0.getMainHandler().post((Runnable)new PServiceFetchAgent$1$1(this));
    }
}
