// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class PServiceWidgetLogEvent
{
    private static String TAG;
    @SerializedName("action")
    public PServiceWidgetLogEvent$WidgetAction action;
    @SerializedName("timeInMs")
    public long timeInMs;
    @SerializedName("widgetSize")
    public PreAppWidgetSize widgetSize;
    
    static {
        PServiceWidgetLogEvent.TAG = "nf_preapp_widgetlogevent";
    }
    
    public PServiceWidgetLogEvent(final PServiceWidgetLogEvent$WidgetAction action, final PreAppWidgetSize widgetSize) {
        this.action = action;
        this.widgetSize = widgetSize;
        this.timeInMs = System.currentTimeMillis();
    }
    
    public static PServiceWidgetLogEvent fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v(PServiceWidgetLogEvent.TAG, "Parsing PServiceWidgetLogEvent from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, PServiceWidgetLogEvent.class);
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d(PServiceWidgetLogEvent.TAG, "PServiceWidgetLogEvent as json: " + json);
        }
        return json;
    }
}
