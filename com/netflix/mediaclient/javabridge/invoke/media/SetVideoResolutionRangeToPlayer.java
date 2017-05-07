// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.media;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public class SetVideoResolutionRangeToPlayer extends BaseInvoke
{
    private static final String METHOD = "setVideoResolutionRange";
    private static final String PROPERTY_maxHeight = "maxHeight";
    private static final String PROPERTY_maxWidth = "maxWidth";
    private static final String PROPERTY_minHeight = "minHeight";
    private static final String PROPERTY_minWidth = "minWidth";
    private static final String TARGET = "media";
    
    public SetVideoResolutionRangeToPlayer(final int n, final int n2, final int n3, final int n4) {
        super("media", "setVideoResolutionRange");
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("minWidth", n);
            jsonObject.put("maxWidth", n2);
            jsonObject.put("minHeight", n3);
            jsonObject.put("maxHeight", n4);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
}
