// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;
import com.netflix.mediaclient.javabridge.ui.LogArguments;
import com.netflix.mediaclient.util.ConnectivityUtils;
import android.content.Intent;
import org.json.JSONException;
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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

class LogblobLoggingImpl$LogblobsSentCallbackImpl implements LogblobLoggingImpl$LogblobsSentCallback
{
    private String deliveryId;
    final /* synthetic */ LogblobLoggingImpl this$0;
    
    public LogblobLoggingImpl$LogblobsSentCallbackImpl(final LogblobLoggingImpl this$0, final String deliveryId) {
        this.this$0 = this$0;
        this.deliveryId = deliveryId;
    }
    
    @Override
    public void onLogblobsSent(final Status status) {
        if (status.isSucces()) {
            Log.d("nf_logblob", "Logblobs are succcesfully sent to backend");
            this.this$0.removeSavedLogblobs(this.deliveryId);
            return;
        }
        Log.e("nf_logblob", "Logblobs are NOT succcesfully sent to backend, do NOT remove them");
        this.this$0.mPendingCachedLogPayloads.remove(this.deliveryId);
    }
}
