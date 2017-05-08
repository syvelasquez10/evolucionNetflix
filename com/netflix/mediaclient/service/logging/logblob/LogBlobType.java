// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

public enum LogBlobType
{
    OFFLINE_CDN_URL_DOWNLOAD("offlinedlreport"), 
    OFFLINE_LOGBLOB_TYPE("offline");
    
    private final String value;
    
    private LogBlobType(final String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
}
