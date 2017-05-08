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
    private static final String PROPERTY_AST = "audioSinkType";
    private static final String PROPERTY_CARRIER_INFO = "carrierinfo";
    private static final String PROPERTY_VOLUME = "volume";
    private static final String TARGET = "media";
    
    public Close(final String s, final PlaybackVolumeMetric playbackVolumeMetric, final JSONObject jsonObject) {
        super("media", "close");
        this.setArguments(s, playbackVolumeMetric, jsonObject);
    }
    
    private void setArguments(final String s, final PlaybackVolumeMetric playbackVolumeMetric, final JSONObject jsonObject) {
        try {
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("audioSinkType", (Object)s);
            if (playbackVolumeMetric != null) {
                jsonObject2.put("volume", playbackVolumeMetric.getVolumeMetric());
            }
            if (jsonObject != null) {
                jsonObject2.put("carrierinfo", (Object)jsonObject);
            }
            this.arguments = jsonObject2.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
