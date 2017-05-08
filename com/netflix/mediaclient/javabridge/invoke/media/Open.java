// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.ui.common.PlayContext;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Open extends BaseInvoke
{
    private static final String METHOD = "open";
    private static final String PARAMS = "params";
    private static final String PROPERTY_CARRIER_INFO = "carrierinfo";
    private static final String PROPERTY_MOVIEID = "movieId";
    private static final String PROPERTY_PREFFERED_PEAK_BPS = "prefferedPeakBps";
    private static final String PROPERTY_PTS = "pts";
    private static final String PROPERTY_TRACKID = "trackerId";
    private static final String PROPERTY_VOLUME = "volume";
    private static final String TARGET = "media";
    
    public Open(final long n, final AuthorizationParams authorizationParams, final long n2, final PlaybackVolumeMetric playbackVolumeMetric, final long n3, final JSONObject jsonObject) {
        super("media", "open");
        this.setArguments(n, authorizationParams, n2, playbackVolumeMetric, n3, jsonObject);
    }
    
    private void setArguments(final long n, final AuthorizationParams authorizationParams, final long n2, final PlaybackVolumeMetric playbackVolumeMetric, final long n3, final JSONObject jsonObject) {
        final PlayContext playContext = authorizationParams.getPlayContext();
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("movieId", n);
            jsonObject2.put("trackerId", playContext.getTrackId());
            jsonObject2.put("pts", n2);
            jsonObject2.put("prefferedPeakBps", n3);
            final JSONObject params = authorizationParams.getParams();
            if (params != null) {
                jsonObject2.put("params", (Object)params);
            }
            if (playbackVolumeMetric != null) {
                jsonObject2.put("volume", playbackVolumeMetric.getVolumeMetric());
            }
            if (jsonObject != null) {
                jsonObject2.put("carrierinfo", (Object)jsonObject);
            }
            this.arguments = jsonObject2.toString();
            if (Log.isLoggable()) {
                Log.d("nf_invoke", String.format("DEBUG info: sessionParams: %s", jsonObject2.toString()));
            }
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
