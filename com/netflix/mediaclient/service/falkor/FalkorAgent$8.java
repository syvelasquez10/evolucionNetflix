// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$8 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$8(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onLoLoMoPrefetched(final Status status) {
        Log.d("FalkorAgent", "fetchPreAppData - prefetch done");
    }
}
