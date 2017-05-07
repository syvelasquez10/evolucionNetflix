// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.annotations.SerializedName;

public class PreAppWidgetSize
{
    private static final String TAG = "nf_preapp_widgetsize";
    @SerializedName("maxHeight")
    public int maxHeight;
    @SerializedName("maxWidth")
    public int maxWidth;
    @SerializedName("minHeight")
    public int minHeight;
    @SerializedName("minWidth")
    public int minWidth;
    
    public PreAppWidgetSize() {
        this.minWidth = 0;
        this.maxWidth = 0;
        this.minHeight = 0;
        this.maxHeight = 0;
    }
    
    public PreAppWidgetSize(final int minWidth, final int maxWidth, final int minHeight, final int maxHeight) {
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_preapp_widgetsize", "widgetSize as json: " + json);
        }
        return json;
    }
}
