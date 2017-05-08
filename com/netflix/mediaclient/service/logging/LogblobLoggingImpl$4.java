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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;

class LogblobLoggingImpl$4 implements DataRepository$DataLoadedCallback
{
    final /* synthetic */ LogblobLoggingImpl this$0;
    final /* synthetic */ String val$deliveryRequestId;
    
    LogblobLoggingImpl$4(final LogblobLoggingImpl this$0, final String val$deliveryRequestId) {
        this.this$0 = this$0;
        this.val$deliveryRequestId = val$deliveryRequestId;
    }
    
    @Override
    public void onDataLoaded(String s, final byte[] array, final long n) {
        if (array == null || array.length < 1) {
            Log.e("nf_logblob", "We failed to retrieve payload. Trying to delete it");
            this.this$0.removeSavedLogblobs(this.val$deliveryRequestId);
            return;
        }
        try {
            s = new String(array, "utf-8");
            this.this$0.sendLogblobs(s, new LogblobLoggingImpl$LogblobsSentCallbackImpl(this.this$0, this.val$deliveryRequestId));
        }
        catch (Throwable t) {
            Log.e("nf_logblob", "Failed to send logblobs.", t);
        }
    }
}
