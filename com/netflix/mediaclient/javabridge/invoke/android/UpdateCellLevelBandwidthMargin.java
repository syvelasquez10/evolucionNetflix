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
    private static final String PROPERTY_KBPS = "downKbps";
    private static final String PROPERTY_LEVEL = "level";
    private static final String TARGET = "media";
    private int downKbps;
    private int signalLevel;
    
    public UpdateCellLevelBandwidthMargin(final int signalLevel, final int downKbps) {
        super("media", "updateSignalStrengths");
        this.signalLevel = signalLevel;
        this.downKbps = downKbps;
        this.setArguments(this.signalLevel, this.downKbps);
    }
    
    private void setArguments(final int n, final int n2) {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("level", n);
            jsonObject.put("downKbps", n2);
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
