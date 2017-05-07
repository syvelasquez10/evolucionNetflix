// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.settings.model;

import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DiscreteEvent;

public class LocalSettingsChangeEvent extends DiscreteEvent
{
    private static final String SETTINGS_CATEGORY = "settings";
    private static final String SETTINGS_CHANGE_NAME = "localSettingChange";
    private String localSettingsLogData;
    
    public LocalSettingsChangeEvent(final String localSettingsLogData) {
        this.category = "settings";
        this.name = "localSettingChange";
        this.localSettingsLogData = localSettingsLogData;
        this.setTime(System.currentTimeMillis());
    }
    
    @Override
    protected JSONObject getCustomData() {
        return new JSONObject(this.localSettingsLogData);
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
