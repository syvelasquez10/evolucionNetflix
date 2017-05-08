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
    private static final String PROPERTY_VOLUME = "volume";
    private static final String TARGET = "media";
    
    public Close(final String s, final PlaybackVolumeMetric playbackVolumeMetric) {
        super("media", "close");
        this.setArguments(s, playbackVolumeMetric);
    }
    
    private void setArguments(final String s, final PlaybackVolumeMetric playbackVolumeMetric) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("audioSinkType", (Object)s);
            if (playbackVolumeMetric != null) {
                jsonObject.put("volume", playbackVolumeMetric.getVolumeMetric());
            }
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
