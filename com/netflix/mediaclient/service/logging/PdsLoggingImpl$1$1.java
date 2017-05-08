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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import com.netflix.mediaclient.util.data.DataRepository;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.data.DataRepository$Entry;
import com.netflix.mediaclient.util.data.DataRepository$LoadedCallback;

class PdsLoggingImpl$1$1 implements DataRepository$LoadedCallback
{
    final /* synthetic */ PdsLoggingImpl$1 this$1;
    
    PdsLoggingImpl$1$1(final PdsLoggingImpl$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onLoaded(final DataRepository$Entry[] array) {
        if (array != null && array.length > 0) {
            this.this$1.this$0.deliverSavedPayloads(array, false);
            return;
        }
        Log.d("nf_pds_logs", "No saved payloads found.");
    }
}
