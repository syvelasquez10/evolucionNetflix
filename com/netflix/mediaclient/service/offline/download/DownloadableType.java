// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

public enum DownloadableType
{
    Audio("nfa"), 
    Subtitle("nfs"), 
    TrickPlay("nfi"), 
    Video("nfv");
    
    private final String mExtension;
    
    private DownloadableType(final String mExtension) {
        this.mExtension = mExtension;
    }
    
    public String getFileExtension() {
        return this.mExtension;
    }
}
