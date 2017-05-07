// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.util.LinkedList;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public final class PServiceLogEvents
{
    public static final Boolean ENABLE_VERBOSE_LOGGING;
    private static final String TAG = "nf_preapp_logevents";
    @SerializedName("widgetLogEvents")
    private List<PServiceWidgetLogEvent> widgetLogEvents;
    
    static {
        ENABLE_VERBOSE_LOGGING = false;
    }
    
    public PServiceLogEvents() {
        this.widgetLogEvents = new LinkedList<PServiceWidgetLogEvent>();
    }
    
    public static PServiceLogEvents createFromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return newInstance();
        }
        if (PServiceLogEvents.ENABLE_VERBOSE_LOGGING && Log.isLoggable()) {
            Log.v("nf_preapp_logevents", "Parsing PServiceLogEvents from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, PServiceLogEvents.class);
    }
    
    public static PServiceLogEvents newInstance() {
        return new PServiceLogEvents();
    }
    
    public void addWidgetEvent(final PServiceWidgetLogEvent pServiceWidgetLogEvent) {
        if (this.widgetLogEvents == null) {
            Log.w("nf_preapp_logevents", "widgetLogEvents is null");
            this.widgetLogEvents = new LinkedList<PServiceWidgetLogEvent>();
        }
        this.widgetLogEvents.add(pServiceWidgetLogEvent);
    }
    
    public List<PServiceWidgetLogEvent> getWidgetEvents() {
        return this.widgetLogEvents;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (PServiceLogEvents.ENABLE_VERBOSE_LOGGING && Log.isLoggable()) {
            Log.d("nf_preapp_logevents", "PServiceLogEvents as json: " + json);
        }
        return json;
    }
}
