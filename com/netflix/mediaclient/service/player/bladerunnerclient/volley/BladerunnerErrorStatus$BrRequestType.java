// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.bladerunnerclient.volley;

public enum BladerunnerErrorStatus$BrRequestType
{
    OfflineDownloadComplete("DC"), 
    OfflineLicense("L"), 
    OfflineLicenseDelete("LD"), 
    OfflineLicenseRefresh("LR"), 
    OfflineLink("I"), 
    OfflineManifest("M"), 
    OfflineManifestRefresh("MR"), 
    StreamingLicense("SL"), 
    other("O");
    
    private String mValue;
    
    private BladerunnerErrorStatus$BrRequestType(final String mValue) {
        this.mValue = mValue;
    }
    
    public String getValue() {
        return this.mValue;
    }
}
