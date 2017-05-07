// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import android.content.Intent;

class PServiceFetchAgent$2 extends PDiskDataRepository$LoadCallback
{
    final /* synthetic */ PServiceFetchAgent this$0;
    final /* synthetic */ Intent val$intent;
    
    PServiceFetchAgent$2(final PServiceFetchAgent this$0, final Intent val$intent) {
        this.this$0 = this$0;
        this.val$intent = val$intent;
    }
    
    @Override
    public void onDataLoaded(final PDiskData pDiskData) {
        this.this$0.getMainHandler().post((Runnable)new PServiceFetchAgent$2$1(this, pDiskData));
    }
}
