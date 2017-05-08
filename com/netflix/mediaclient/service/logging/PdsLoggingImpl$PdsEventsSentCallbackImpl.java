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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;

class PdsLoggingImpl$PdsEventsSentCallbackImpl implements PdsLoggingImpl$PdsEventsSentCallback
{
    private String deliveryId;
    final /* synthetic */ PdsLoggingImpl this$0;
    
    public PdsLoggingImpl$PdsEventsSentCallbackImpl(final PdsLoggingImpl this$0, final String deliveryId) {
        this.this$0 = this$0;
        this.deliveryId = deliveryId;
    }
    
    @Override
    public void onPdsEventsSent(final Status status) {
        if (status.isSucces()) {
            Log.d("nf_pds_logs", "Pds events are succcesfully sent to backend");
            this.this$0.removeSavedPdsEvents(this.deliveryId);
            return;
        }
        Log.e("nf_pds_logs", "pds events are NOT succcesfully sent to backend, do NOT remove them");
        this.this$0.mPendingCachedLogPayloads.remove(this.deliveryId);
    }
}
