// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.servicemgr.interface_.offline;

public enum DownloadVideoQuality
{
    BEST("BEST"), 
    DEFAULT("DEFAULT"), 
    UNKNOWN("");
    
    private final String value;
    
    private DownloadVideoQuality(final String value) {
        this.value = value;
    }
    
    public static DownloadVideoQuality create(final String s) {
        final DownloadVideoQuality[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final DownloadVideoQuality downloadVideoQuality = values[i];
            if (downloadVideoQuality.value.equalsIgnoreCase(s)) {
                return downloadVideoQuality;
            }
        }
        return DownloadVideoQuality.DEFAULT;
    }
    
    public String getValue() {
        return this.value;
    }
}
