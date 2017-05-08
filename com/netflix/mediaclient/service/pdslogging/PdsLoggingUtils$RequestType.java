// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pdslogging;

public enum PdsLoggingUtils$RequestType
{
    LICENSE("l"), 
    MANIFEST("m"), 
    OTHER("0");
    
    private String mValue;
    
    private PdsLoggingUtils$RequestType(final String mValue) {
        this.mValue = mValue;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
