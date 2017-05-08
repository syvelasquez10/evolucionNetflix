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
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;
import com.netflix.mediaclient.Log;

class PdsLoggingImpl$1 implements Runnable
{
    final /* synthetic */ PdsLoggingImpl this$0;
    
    PdsLoggingImpl$1(final PdsLoggingImpl this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("nf_pds_logs", "Check if we have not delivered events from last time our app was runnung...");
        this.this$0.mDataRepository.loadAll((DataRepository$LoadedCallback)new PdsLoggingImpl$1$1(this));
    }
}
