// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.media.bitrate.AudioBitrateRange;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetAudioBitrateRange extends BaseInvoke
{
    private static final String METHOD = "setAudioBitrateRange";
    private static final String PROPERTY_maxBitrate = "maxBitrate";
    private static final String PROPERTY_minBitrate = "minBitrate";
    private static final String TARGET = "media";
    
    public SetAudioBitrateRange(final int n, final int n2) {
        super("media", "setAudioBitrateRange");
        this.setArguments(n, n2);
    }
    
    public SetAudioBitrateRange(final AudioBitrateRange audioBitrateRange) {
        super("media", "setAudioBitrateRange");
        if (audioBitrateRange == null) {
            throw new IllegalArgumentException("Range can not be null!");
        }
        this.setArguments(audioBitrateRange.getMinimal(), audioBitrateRange.getMaximal());
    }
    
    private void setArguments(final int n, final int n2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("minBitrate", n);
            jsonObject.put("maxBitrate", n2);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
