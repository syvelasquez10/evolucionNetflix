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
import com.netflix.mediaclient.util.data.DataRepository$DataLoadedCallback;

class PdsLoggingImpl$4 implements DataRepository$DataLoadedCallback
{
    final /* synthetic */ PdsLoggingImpl this$0;
    final /* synthetic */ String val$deliveryRequestId;
    
    PdsLoggingImpl$4(final PdsLoggingImpl this$0, final String val$deliveryRequestId) {
        this.this$0 = this$0;
        this.val$deliveryRequestId = val$deliveryRequestId;
    }
    
    public void onDataLoaded(String s, final byte[] array, final long n) {
        if (array == null || array.length < 1) {
            Log.e("nf_pds_logs", "We failed to retrieve payload. Trying to delete it");
            this.this$0.removeSavedPdsEvents(this.val$deliveryRequestId);
            return;
        }
        try {
            s = new String(array, "utf-8");
            this.this$0.sendSavedPdsEventBundle(s, new PdsLoggingImpl$PdsEventsSentCallbackImpl(this.this$0, this.val$deliveryRequestId));
        }
        catch (Throwable t) {
            Log.e("nf_pds_logs", "Failed to send pdsEvent.", t);
        }
    }
}
