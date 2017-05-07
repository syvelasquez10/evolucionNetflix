// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

public enum PDiskData$ListType
{
    BILLBOARD("Spotlight"), 
    CW("ContinueWatching"), 
    IQ("MyList"), 
    LOMO_INFO("lomoInfo"), 
    NON_MEMBER("nonMember"), 
    STANDARD_FIRST("arFirst"), 
    STANDARD_SECOND("arSecond"), 
    UNKNOWN("");
    
    private String value;
    
    private PDiskData$ListType(final String value) {
        this.value = value;
    }
    
    public static PDiskData$ListType create(final String s) {
        final PDiskData$ListType[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final PDiskData$ListType pDiskData$ListType = values[i];
            if (pDiskData$ListType.value.equalsIgnoreCase(s)) {
                return pDiskData$ListType;
            }
        }
        return PDiskData$ListType.UNKNOWN;
    }
    
    public String getValue() {
        return this.value;
    }
}
