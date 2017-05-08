// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Map;
import java.util.HashMap;
import com.netflix.mediaclient.service.logging.perf.InteractiveTracker$InteractiveListener;

class DetailsActivity$1 implements InteractiveTracker$InteractiveListener
{
    final /* synthetic */ DetailsActivity this$0;
    
    DetailsActivity$1(final DetailsActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onInteractive() {
        this.this$0.endDPTTRSession(new HashMap<String, String>());
    }
}
