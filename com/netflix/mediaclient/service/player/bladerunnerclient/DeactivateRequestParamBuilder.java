// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;

public class DeactivateRequestParamBuilder extends LinksParamBuilder
{
    private boolean mWasDownloadComplete;
    
    public DeactivateRequestParamBuilder(final boolean mWasDownloadComplete) {
        this.mWasDownloadComplete = mWasDownloadComplete;
    }
    
    @Override
    final String build() {
        return this.getRequestObject(this.getParams()).toString();
    }
    
    @Override
    protected JSONObject getParams() {
        final JSONObject params = super.getParams();
        try {
            params.put("downloadCompleted", this.mWasDownloadComplete);
            return params;
        }
        catch (JSONException ex) {
            Log.e("nf_msl_volley_bladerunner", (Throwable)ex, "error creating params", new Object[0]);
            return params;
        }
    }
}
