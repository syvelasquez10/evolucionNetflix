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
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.LogblobLogging;
import java.util.List;
import com.netflix.mediaclient.servicemgr.Logblob;
import com.netflix.mediaclient.util.EventQueue;

class LogblobLoggingImpl$LogblobQueue extends EventQueue<Logblob>
{
    final /* synthetic */ LogblobLoggingImpl this$0;
    
    public LogblobLoggingImpl$LogblobQueue(final LogblobLoggingImpl this$0) {
        this.this$0 = this$0;
        super("nf_logblob_queue", 30, 60000L, true, true);
    }
    
    @Override
    protected void doFlush(final List<Logblob> list, final boolean b) {
        this.this$0.sendEvents(list, b);
    }
}
