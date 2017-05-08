// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logpds.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import org.json.JSONArray;
import com.netflix.mediaclient.service.logging.PdsLoggingImpl$PdsEventsSentCallback;
import org.json.JSONObject;
import com.netflix.mediaclient.service.msl.volley.ApiFalkorMSLVolleyRequest;

public class SendPdsEventsMSLRequest extends ApiFalkorMSLVolleyRequest<String>
{
    private static final String TAG = "nf_msl_volley_SendPdsBundle";
    private static final String pqlQuery1 = "['pdsEventBundle']";
    private final JSONObject mPdsBundle;
    private final PdsLoggingImpl$PdsEventsSentCallback responseCallback;
    
    public SendPdsEventsMSLRequest(final String[] array, final PdsLoggingImpl$PdsEventsSentCallback responseCallback) {
        final JSONObject mPdsBundle = new JSONObject();
        while (true) {
            try {
                final JSONArray jsonArray = new JSONArray();
                for (int length = array.length, i = 0; i < length; ++i) {
                    jsonArray.put((Object)new JSONObject(array[i]));
                }
                mPdsBundle.put("pdsBundle", (Object)jsonArray);
                this.mPdsBundle = mPdsBundle;
                this.responseCallback = responseCallback;
            }
            catch (Exception ex) {
                Log.e("nf_msl_volley_SendPdsBundle", "error in creating json array");
                continue;
            }
            break;
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList("['pdsEventBundle']");
    }
    
    @Override
    protected Map<String, String> getParams() {
        final Map<String, String> params = super.getParams();
        params.put("bladerunnerParams", this.mPdsBundle.toString());
        return params;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onPdsEventsSent(status);
            return;
        }
        Log.w("nf_msl_volley_SendPdsBundle", "callback null?");
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            this.responseCallback.onPdsEventsSent(CommonStatus.OK);
            return;
        }
        Log.w("nf_msl_volley_SendPdsBundle", "callback null?");
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        return s;
    }
}
