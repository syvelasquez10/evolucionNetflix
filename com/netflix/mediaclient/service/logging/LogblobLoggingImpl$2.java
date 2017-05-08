// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import org.json.JSONException;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.logging.logblob.volley.SendLogblobsMSLRequest;
import com.netflix.mediaclient.service.logging.logblob.LogblobUtils;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.Logblob$CommonParams;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.LogblobLogging;

class LogblobLoggingImpl$2 implements Runnable
{
    final /* synthetic */ LogblobLoggingImpl this$0;
    final /* synthetic */ String val$deliveryRequestId;
    
    LogblobLoggingImpl$2(final LogblobLoggingImpl this$0, final String val$deliveryRequestId) {
        this.this$0 = this$0;
        this.val$deliveryRequestId = val$deliveryRequestId;
    }
    
    @Override
    public void run() {
        this.this$0.loadAndSendLogblobs(this.val$deliveryRequestId);
    }
}
