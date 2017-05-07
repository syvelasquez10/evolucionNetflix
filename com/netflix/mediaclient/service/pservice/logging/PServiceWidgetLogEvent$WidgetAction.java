// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.google.gson.annotations.SerializedName;

public enum PServiceWidgetLogEvent$WidgetAction
{
    DELETE("delete"), 
    GO_TO_NEXT("next"), 
    INSTALL("install"), 
    UNKNOWN("");
    
    @SerializedName("value")
    private String value;
    
    private PServiceWidgetLogEvent$WidgetAction(final String value) {
        this.value = value;
    }
    
    public static PServiceWidgetLogEvent$WidgetAction create(final String s) {
        final PServiceWidgetLogEvent$WidgetAction[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PServiceWidgetLogEvent$WidgetAction pServiceWidgetLogEvent$WidgetAction = values[i];
            if (pServiceWidgetLogEvent$WidgetAction.value.equalsIgnoreCase(s)) {
                return pServiceWidgetLogEvent$WidgetAction;
            }
        }
        return PServiceWidgetLogEvent$WidgetAction.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
