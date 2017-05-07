// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.android.preapp.model;

import com.google.gson.annotations.SerializedName;

public enum PreAppWidgetInstallEvent$WidgetInstallAction
{
    ADD("androidAddWidget"), 
    DELETE("androidDeleteWidget"), 
    UNKNOWN("");
    
    @SerializedName("value")
    private String value;
    
    private PreAppWidgetInstallEvent$WidgetInstallAction(final String value) {
        this.value = value;
    }
    
    public static PreAppWidgetInstallEvent$WidgetInstallAction create(final String s) {
        final PreAppWidgetInstallEvent$WidgetInstallAction[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PreAppWidgetInstallEvent$WidgetInstallAction preAppWidgetInstallEvent$WidgetInstallAction = values[i];
            if (preAppWidgetInstallEvent$WidgetInstallAction.value.equalsIgnoreCase(s)) {
                return preAppWidgetInstallEvent$WidgetInstallAction;
            }
        }
        return PreAppWidgetInstallEvent$WidgetInstallAction.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
