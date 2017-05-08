// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;
import com.netflix.mediaclient.util.data.FileSystemDataRepositoryImpl;
import java.io.File;
import com.netflix.mediaclient.service.msl.volley.MSLVolleyRequest;
import com.netflix.mediaclient.service.logging.logpds.volley.SendPdsEventsMSLRequest;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import java.util.List;
import com.netflix.mediaclient.util.EventQueue;

class PdsLoggingImpl$PdsEventQueue extends EventQueue<String>
{
    final /* synthetic */ PdsLoggingImpl this$0;
    
    public PdsLoggingImpl$PdsEventQueue(final PdsLoggingImpl this$0) {
        this.this$0 = this$0;
        super("nf_pds_queue", 6, 120000L, true, true);
    }
    
    protected void doFlush(final List<String> list, final boolean b) {
        this.this$0.flushEventsInQueue(list, b);
    }
}
