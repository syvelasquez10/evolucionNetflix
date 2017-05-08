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
import com.netflix.mediaclient.servicemgr.Logblob$CommonParams;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;
import com.netflix.mediaclient.Log;

class LogblobLoggingImpl$1 implements Runnable
{
    final /* synthetic */ LogblobLoggingImpl this$0;
    
    LogblobLoggingImpl$1(final LogblobLoggingImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_logblob", "Check if we have not delivered events from last time our app was runnung...");
        this.this$0.mDataRepository.loadAll(new LogblobLoggingImpl$1$1(this));
    }
}
