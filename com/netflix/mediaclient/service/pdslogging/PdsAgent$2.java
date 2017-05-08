// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

import org.json.JSONObject;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentListener;

class PdsAgent$2 implements Runnable
{
    final /* synthetic */ PdsAgent this$0;
    
    PdsAgent$2(final PdsAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.getOfflineAgent().removeOfflineAgentListener(this.this$0.mDownloadManager);
    }
}
