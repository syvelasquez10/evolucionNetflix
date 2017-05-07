// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import com.google.gson.annotations.SerializedName;

public enum PreAppWidgetLogActionData$PreAppWidgetActionName
{
    GO_TO_NEXT("next"), 
    HOME("home"), 
    START_PLAY("startPlay"), 
    UNKNOWN("unknown"), 
    VIEW_TITLE_DETAILS("viewTitleDetails");
    
    @SerializedName("value")
    private String value;
    
    private PreAppWidgetLogActionData$PreAppWidgetActionName(final String value) {
        this.value = value;
    }
    
    public static PreAppWidgetLogActionData$PreAppWidgetActionName create(final String s) {
        final PreAppWidgetLogActionData$PreAppWidgetActionName[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PreAppWidgetLogActionData$PreAppWidgetActionName preAppWidgetLogActionData$PreAppWidgetActionName = values[i];
            if (preAppWidgetLogActionData$PreAppWidgetActionName.value.equalsIgnoreCase(s)) {
                return preAppWidgetLogActionData$PreAppWidgetActionName;
            }
        }
        return PreAppWidgetLogActionData$PreAppWidgetActionName.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
