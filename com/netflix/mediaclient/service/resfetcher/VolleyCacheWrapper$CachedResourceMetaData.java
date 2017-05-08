// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.resfetcher;

public class VolleyCacheWrapper$CachedResourceMetaData
{
    final long length;
    final String localPath;
    final long offset;
    final /* synthetic */ VolleyCacheWrapper this$0;
    
    public VolleyCacheWrapper$CachedResourceMetaData(final VolleyCacheWrapper this$0, final String localPath, final long offset, final long length) {
        this.this$0 = this$0;
        this.length = length;
        this.localPath = localPath;
        this.offset = offset;
    }
    
    public long getByteLength() {
        return this.length;
    }
    
    public long getByteOffset() {
        return this.offset;
    }
    
    public String getLocalPath() {
        return this.localPath;
    }
}
