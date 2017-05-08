// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.falkor;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.browse.SimpleBrowseAgentCallback;

class FalkorAgent$7 extends SimpleBrowseAgentCallback
{
    final /* synthetic */ FalkorAgent this$0;
    
    FalkorAgent$7(final FalkorAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onBrowsePlaySessionEnd(final boolean b, final Status status) {
        if (!b) {
            Log.w("FalkorAgent", "onBrowsePlaySessionEnd() returned false - something wrong with backend!");
            ErrorLoggingManager.logHandledException("SPY-8604 - Got false response from the server");
        }
    }
}
