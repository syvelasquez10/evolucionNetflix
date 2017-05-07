// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class LoLoMoAdapter$1 extends LoggingManagerCallback
{
    final /* synthetic */ LoLoMoAdapter this$0;
    final /* synthetic */ long val$requestIdClone;
    
    LoLoMoAdapter$1(final LoLoMoAdapter this$0, final String s, final long val$requestIdClone) {
        this.this$0 = this$0;
        this.val$requestIdClone = val$requestIdClone;
        super(s);
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        super.onLoLoMoPrefetched(status);
        ThreadUtils.assertOnMain();
        if (this.val$requestIdClone != this.this$0.requestId) {
            Log.d("LoLoMoAdapter", "Request IDs do not match - skipping prefetch callback");
            return;
        }
        this.this$0.handlePrefetchComplete();
    }
}
