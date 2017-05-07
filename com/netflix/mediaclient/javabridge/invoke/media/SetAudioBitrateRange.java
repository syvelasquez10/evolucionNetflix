// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import com.netflix.mediaclient.Log;
import org.json.JSONException;
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
        JSONObject jsonObject = null;
        try {
            final JSONObject jsonObject2;
            jsonObject = (jsonObject2 = new JSONObject());
            final String s = "minBitrate";
            final int n3 = n;
            jsonObject2.put(s, n3);
            final JSONObject jsonObject3 = jsonObject;
            final String s2 = "maxBitrate";
            final int n4 = n2;
            jsonObject3.put(s2, n4);
            final SetAudioBitrateRange setAudioBitrateRange = this;
            final JSONObject jsonObject4 = jsonObject;
            final String s3 = jsonObject4.toString();
            setAudioBitrateRange.arguments = s3;
            return;
        }
        catch (JSONException ex) {}
        while (true) {
            try {
                final JSONObject jsonObject2 = jsonObject;
                final String s = "minBitrate";
                final int n3 = n;
                jsonObject2.put(s, n3);
                final JSONObject jsonObject3 = jsonObject;
                final String s2 = "maxBitrate";
                final int n4 = n2;
                jsonObject3.put(s2, n4);
                final SetAudioBitrateRange setAudioBitrateRange = this;
                final JSONObject jsonObject4 = jsonObject;
                final String s3 = jsonObject4.toString();
                setAudioBitrateRange.arguments = s3;
                return;
                Log.e("nf_invoke", "Failed to create JSON object", (Throwable)jsonObject);
            }
            catch (JSONException jsonObject) {
                continue;
            }
            break;
        }
    }
}
