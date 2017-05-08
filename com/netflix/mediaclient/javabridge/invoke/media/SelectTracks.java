// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.media.AudioSource;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SelectTracks extends BaseInvoke
{
    private static final String METHOD = "selectTracks";
    private static final String PROPERTY_audio = "audio";
    private static final String PROPERTY_subtitle = "subtitle";
    private static final String TARGET = "media";
    
    public SelectTracks(final AudioSource audioSource, final Subtitle subtitle) {
        super("media", "selectTracks");
        if (audioSource == null) {
            throw new IllegalArgumentException("Audio can not be null!");
        }
        this.setArguments(audioSource, subtitle);
    }
    
    private void setArguments(final AudioSource audioSource, final Subtitle subtitle) {
        while (true) {
            Label_0086: {
                if (audioSource == null) {
                    break Label_0086;
                }
                try {
                    if (audioSource.getId() != null) {
                        final String id = audioSource.getId();
                        String id2;
                        if (subtitle != null && subtitle.getId() != null) {
                            id2 = subtitle.getId();
                        }
                        else {
                            id2 = "-1";
                        }
                        final JSONObject jsonObject = new JSONObject();
                        jsonObject.put("audio", (Object)id);
                        jsonObject.put("subtitle", (Object)id2);
                        this.arguments = jsonObject.toString();
                        return;
                    }
                }
                catch (JSONException ex) {
                    Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
                    return;
                }
            }
            final String id = "";
            continue;
        }
    }
}
