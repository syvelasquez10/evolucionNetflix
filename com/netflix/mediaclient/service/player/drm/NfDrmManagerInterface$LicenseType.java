// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.drm;

public enum NfDrmManagerInterface$LicenseType
{
    LICENSE_TYPE_LDL(1, "LDL"), 
    LICENSE_TYPE_OFFLINE(3, "OFFLINE"), 
    LICENSE_TYPE_STANDARD(2, "STANDARD");
    
    private String description;
    private int type;
    
    private NfDrmManagerInterface$LicenseType(final int type, final String description) {
        this.type = type;
        this.description = description;
    }
}
