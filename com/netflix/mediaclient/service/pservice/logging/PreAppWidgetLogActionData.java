// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.annotations.SerializedName;

public class PreAppWidgetLogActionData
{
    public static final String EXTRA_WIDGET_ACTION_DATA = "widgetActionData";
    private static final String INPUT_GESTURE = "gesture";
    private static final String TAG = "nf_preapp_widgetLogActionData";
    @SerializedName("inputMethod")
    private String inputMethod;
    @SerializedName("inputValue")
    private String inputValue;
    @SerializedName("isHotKey")
    private boolean isHotKey;
    @SerializedName("name")
    private String name;
    
    public PreAppWidgetLogActionData(final PreAppWidgetLogActionData$PreAppWidgetActionName preAppWidgetLogActionData$PreAppWidgetActionName) {
        this.name = preAppWidgetLogActionData$PreAppWidgetActionName.getValue();
        this.isHotKey = false;
        this.inputMethod = "gesture";
        this.inputValue = preAppWidgetLogActionData$PreAppWidgetActionName.getValue();
    }
    
    public static PreAppWidgetLogActionData createInstance(final String s) {
        return new PreAppWidgetLogActionData(PreAppWidgetLogActionData$PreAppWidgetActionName.create(s));
    }
    
    public String getName() {
        return this.name;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_preapp_widgetLogActionData", "PreAppWidgetLogActionData as json: " + json);
        }
        return json;
    }
}
