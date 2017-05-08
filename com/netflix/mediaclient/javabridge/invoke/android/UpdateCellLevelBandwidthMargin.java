// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.invoke.android;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import com.netflix.mediaclient.javabridge.invoke.BaseInvoke;

public final class UpdateCellLevelBandwidthMargin extends BaseInvoke
{
    private static final String METHOD = "updateSignalStrengths";
    private static final String PROPERTY_LEVEL = "level";
    private static final String TARGET = "media";
    private int signalLevel;
    
    public UpdateCellLevelBandwidthMargin(final int signalLevel) {
        super("media", "updateSignalStrengths");
        this.setArguments(this.signalLevel = signalLevel);
    }
    
    private void setArguments(final int n) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("level", n);
            this.arguments = jsonObject.toString();
        }
        catch (JSONException ex) {
            Log.e("nf_invoke", "Failed to create JSON object", (Throwable)ex);
        }
    }
    
    public int getLevel() {
        return this.signalLevel;
    }
}
