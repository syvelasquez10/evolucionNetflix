// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.util.PlaybackVolumeMetric;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class VolumeChange extends BaseInvoke
{
    private static final String METHOD = "volumeChange";
    private static final String PROPERTY_OLD_VOLUME = "oldvolume";
    private static final String PROPERTY_VOLUME = "volume";
    private static final String TARGET = "media";
    
    public VolumeChange(final PlaybackVolumeMetric playbackVolumeMetric, final PlaybackVolumeMetric playbackVolumeMetric2) {
        super("media", "volumeChange");
        this.setArguments(playbackVolumeMetric, playbackVolumeMetric2);
    }
    
    private void setArguments(final PlaybackVolumeMetric playbackVolumeMetric, final PlaybackVolumeMetric playbackVolumeMetric2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("oldvolume", playbackVolumeMetric.getVolumeMetric());
            jsonObject.put("volume", playbackVolumeMetric2.getVolumeMetric());
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
