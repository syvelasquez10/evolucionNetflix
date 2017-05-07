// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.util.DeviceCategory;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.google.gson.annotations.SerializedName;

public final class PreAppWidgetLogData
{
    public static final String EXTRA_WIDGET_LOG_DATA = "logData";
    private static final String TAG = "nf_preapp_widgetLogData";
    @SerializedName("deviceType")
    private String deviceCategroy;
    @SerializedName("isMember")
    private boolean isMember;
    @SerializedName("widgetSize")
    private String widgetSize;
    @SerializedName("widgetType")
    private String widgetType;
    
    public PreAppWidgetLogData(final String widgetType, final String widgetSize, final String deviceCategroy, final boolean isMember) {
        this.widgetType = widgetType;
        this.widgetSize = widgetSize;
        this.deviceCategroy = deviceCategroy;
        this.isMember = isMember;
    }
    
    public static PreAppWidgetLogData createInstance(final Context context, final int n, final boolean b) {
        return createInstance(context, PServiceLogging.getWidgetSize(context, n), b);
    }
    
    public static PreAppWidgetLogData createInstance(final Context context, final PreAppWidgetSize preAppWidgetSize, final boolean b) {
        String s;
        if (DeviceUtils.isTabletByContext(context)) {
            s = DeviceCategory.TABLET.getValue();
        }
        else {
            s = DeviceCategory.PHONE.getValue();
        }
        return new PreAppWidgetLogData("default", preAppWidgetSize.toJsonString(), s, b);
    }
    
    public boolean isMember() {
        return this.isMember;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_preapp_widgetLogData", "PreAppWidgetLogData as json: " + json);
        }
        return json;
    }
}
