// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher.volley;

public class LocalCachedFileMetadata
{
    long byteOffset;
    long byteSize;
    String localUrl;
    
    public LocalCachedFileMetadata(final String localUrl, final long byteOffset, final long byteSize) {
        this.localUrl = localUrl;
        this.byteOffset = byteOffset;
        this.byteSize = byteSize;
    }
    
    public long getByteOffset() {
        return this.byteOffset;
    }
    
    public long getByteSize() {
        return this.byteSize;
    }
    
    public String getLocalUrl() {
        return this.localUrl;
    }
}
