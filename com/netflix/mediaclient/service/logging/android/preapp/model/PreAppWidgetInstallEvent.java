// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.preapp.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class PreAppWidgetInstallEvent extends DiscreteEvent
{
    private String widgetLogData;
    
    public PreAppWidgetInstallEvent(final PreAppWidgetInstallEvent$WidgetInstallAction preAppWidgetInstallEvent$WidgetInstallAction, final String widgetLogData, final long time) {
        this.category = "preAppAndroid";
        this.name = preAppWidgetInstallEvent$WidgetInstallAction.getValue();
        this.widgetLogData = widgetLogData;
        this.setTime(time);
    }
    
    @Override
    protected JSONObject getCustomData() {
        return new JSONObject(this.widgetLogData);
    }
    
    @Override
    protected JSONObject getData() {
        JSONObject data;
        if ((data = super.getData()) == null) {
            data = new JSONObject();
        }
        return data;
    }
}
