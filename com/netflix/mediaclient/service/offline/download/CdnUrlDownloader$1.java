// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.util.VolleyUtils;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Looper;
import com.android.volley.RequestQueue;
import java.util.List;
import java.io.File;
import android.content.Context;
import android.os.Handler;

class CdnUrlDownloader$1 implements Runnable
{
    final /* synthetic */ CdnUrlDownloader this$0;
    
    CdnUrlDownloader$1(final CdnUrlDownloader this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.onCdnRetryRunnable();
    }
}
