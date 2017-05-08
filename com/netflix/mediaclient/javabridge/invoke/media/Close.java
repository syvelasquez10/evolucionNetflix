// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class Close extends BaseInvoke
{
    private static final String METHOD = "close";
    private static final String PROPERTY_ACTIVITY_DATA = "activitydata";
    private static final String PROPERTY_AST = "audioSinkType";
    private static final String PROPERTY_CARRIER_INFO = "carrierinfo";
    private static final String PROPERTY_VOLUME = "volume";
    private static final String TARGET = "media";
    
    public Close(final String s, final PlaybackVolumeMetric playbackVolumeMetric, final JSONObject jsonObject, final JSONObject jsonObject2) {
        super("media", "close");
        this.setArguments(s, playbackVolumeMetric, jsonObject, jsonObject2);
    }
    
    private void setArguments(final String s, final PlaybackVolumeMetric playbackVolumeMetric, final JSONObject jsonObject, final JSONObject jsonObject2) {
        try {
            final JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("audioSinkType", (Object)s);
            if (playbackVolumeMetric != null) {
                jsonObject3.put("volume", playbackVolumeMetric.getVolumeMetric());
            }
            if (jsonObject != null) {
                jsonObject3.put("carrierinfo", (Object)jsonObject);
            }
            if (jsonObject2 != null) {
                jsonObject3.put("activitydata", (Object)jsonObject2);
            }
            this.arguments = jsonObject3.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
