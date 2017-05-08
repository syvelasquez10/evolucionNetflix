// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import java.util.Map;
import java.util.Arrays;
import android.os.Build;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import org.json.JSONArray;
import com.netflix.mediaclient.servicemgr.Logblob$CommonParams;
import com.netflix.mediaclient.servicemgr.Logblob;
import java.util.List;
import android.content.Context;
import com.netflix.mediaclient.service.logging.LogblobLoggingImpl$LogblobsSentCallback;
import org.json.JSONObject;
import java.util.concurrent.atomic.AtomicLong;
import com.netflix.mediaclient.service.msl.volley.ApiFalkorMSLVolleyRequest;

public class SendLogblobsMSLRequest extends ApiFalkorMSLVolleyRequest<String>
{
    private static final String TAG = "nf_msl_volley_SendLogblobs";
    private static final String pqlQuery1 = "['logblobs']";
    private static final AtomicLong sLogSequenceNumber;
    private final JSONObject mLogblobs;
    private final LogblobLoggingImpl$LogblobsSentCallback responseCallback;
    
    static {
        sLogSequenceNumber = new AtomicLong(0L);
    }
    
    public SendLogblobsMSLRequest(final Context context, final List<Logblob> list, final Logblob$CommonParams logblob$CommonParams, final LogblobLoggingImpl$LogblobsSentCallback responseCallback) {
        this.mLogblobs = this.createLogblob(context, SendLogblobsMSLRequest.sLogSequenceNumber.incrementAndGet(), logblob$CommonParams, list);
        this.responseCallback = responseCallback;
    }
    
    private long convertToServerEpoch(final long n) {
        return n;
    }
    
    private JSONObject createLogblob(final Context context, final long n, final Logblob$CommonParams logblob$CommonParams, final List<Logblob> list) {
        final JSONObject jsonObject = new JSONObject();
        final JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); ++i) {
            final JSONObject json = list.get(i).toJson();
            json.put("clienttime", this.convertToServerEpoch(list.get(i).getClientEpoch()));
            json.put("snum", n);
            json.put("lnum", i + 1);
            json.put("esn", (Object)logblob$CommonParams.esn);
            json.put("devmod", (Object)logblob$CommonParams.deviceModel);
            json.put("lver", (Object)"2013.2");
            json.put("sdkver", (Object)"4.1");
            json.put("platformVersion", (Object)AndroidManifestUtils.getVersion(context));
            json.put("platformBuildNum", AndroidManifestUtils.getVersionCode(context));
            json.put("platformType", (Object)"Android Sidecar");
            json.put("uiver", (Object)AndroidManifestUtils.getVersion(context));
            json.put("via", (Object)"br");
            json.put("fingerprint", (Object)Build.FINGERPRINT);
            jsonArray.put((Object)json);
        }
        jsonObject.put("entries", (Object)jsonArray);
        return jsonObject;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['logblobs']");
    }
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("method", (Object)"logblob");
                jsonObject.put("logblobs", (Object)this.mLogblobs);
                params.put("bladerunnerParams", jsonObject.toString());
                return params;
            }
            catch (Exception ex) {
                Log.e("nf_msl_volley_SendLogblobs", "error creating logblob params", ex);
                continue;
            }
            break;
        }
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onLogblobsSent(status);
            return;
        }
        Log.w("nf_msl_volley_SendLogblobs", "callback null?");
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            this.responseCallback.onLogblobsSent(CommonStatus.OK);
            return;
        }
        Log.w("nf_msl_volley_SendLogblobs", "callback null?");
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.d("nf_msl_volley_SendLogblobs", "parseFalkorResponse " + s);
        }
        return s;
    }
}
