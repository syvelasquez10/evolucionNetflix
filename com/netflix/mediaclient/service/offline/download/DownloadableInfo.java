// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.List;

interface DownloadableInfo
{
    List<CdnUrl> getCdnUrls();
    
    String getDownloadableId();
    
    DownloadableType getDownloadableType();
    
    long getSizeOfDownloadable();
}
