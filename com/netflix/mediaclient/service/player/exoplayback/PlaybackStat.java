// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import org.json.JSONException;
import org.json.JSONObject;

public class PlaybackStat
{
    private String decoderName;
    private int mDroppedFrames;
    private long mElapsedTime;
    
    public PlaybackStat(final String decoderName) {
        this.decoderName = decoderName;
    }
    
    JSONObject getJSON() {
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("decName", (Object)this.decoderName);
            jsonObject.put("droppedFrames", this.mDroppedFrames);
            jsonObject.put("elapsedTime", this.mElapsedTime);
            return jsonObject;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
    }
    
    public void updateDecoderStat(final int n, final long n2) {
        this.mDroppedFrames += n;
        this.mElapsedTime += n2;
    }
}
