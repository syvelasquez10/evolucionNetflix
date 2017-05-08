// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.Logblob;

class CdnUrlDownloadEventReceiver$1 implements Runnable
{
    final /* synthetic */ CdnUrlDownloadEventReceiver this$0;
    final /* synthetic */ CdnUrlDownloadEventReceiver$CdnDownloadLogBlob val$cdnDownloadLogBlob;
    
    CdnUrlDownloadEventReceiver$1(final CdnUrlDownloadEventReceiver this$0, final CdnUrlDownloadEventReceiver$CdnDownloadLogBlob val$cdnDownloadLogBlob) {
        this.this$0 = this$0;
        this.val$cdnDownloadLogBlob = val$cdnDownloadLogBlob;
    }
    
    @Override
    public void run() {
        this.this$0.mLogblobLogging.sendLogblob(this.val$cdnDownloadLogBlob);
    }
}
