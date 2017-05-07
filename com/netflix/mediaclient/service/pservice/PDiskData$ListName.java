// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

public enum PDiskData$ListName
{
    BILLBOARD("Spotlight"), 
    CW("ContinueWatching"), 
    IQ("MyList"), 
    STANDARD_FIRST("RecommendedList#1"), 
    STANDARD_SECOND("RecommendedList#2"), 
    UNKNOWN("");
    
    private String value;
    
    private PDiskData$ListName(final String value) {
        this.value = value;
    }
    
    public static PDiskData$ListName create(final String s) {
        final PDiskData$ListName[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PDiskData$ListName pDiskData$ListName = values[i];
            if (pDiskData$ListName.value.equalsIgnoreCase(s)) {
                return pDiskData$ListName;
            }
        }
        return PDiskData$ListName.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
