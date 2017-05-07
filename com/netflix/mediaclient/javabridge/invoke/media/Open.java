// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.ui.common.PlayContext;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Open extends BaseInvoke
{
    private static final String METHOD = "open";
    private static final String PARAMS = "params";
    private static final String PROPERTY_MOVIEID = "movieId";
    private static final String PROPERTY_PTS = "pts";
    private static final String PROPERTY_TRACKID = "trackerId";
    private static final String TARGET = "media";
    
    public Open(final long n, final AuthorizationParams authorizationParams, final long n2) {
        super("media", "open");
        this.setArguments(n, authorizationParams, n2);
    }
    
    private void setArguments(final long n, final AuthorizationParams authorizationParams, final long n2) {
        final PlayContext playContext = authorizationParams.getPlayContext();
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("movieId", n);
            jsonObject.put("trackerId", playContext.getTrackId());
            jsonObject.put("pts", n2);
            final JSONObject params = authorizationParams.getParams();
            if (params != null) {
                jsonObject.put("params", (Object)params);
            }
            this.arguments = jsonObject.toString();
            if (Log.isLoggable()) {
                Log.d("nf_invoke", String.format("DEBUG info: sessionParams: %s", jsonObject.toString()));
            }
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
