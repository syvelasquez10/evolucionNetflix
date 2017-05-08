// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import com.facebook.common.internal.Preconditions;
import java.io.File;
import com.facebook.binaryresource.FileBinaryResource;

class DefaultDiskStorage$EntryImpl implements DiskStorage$Entry
{
    private final String id;
    private final FileBinaryResource resource;
    private long size;
    private long timestamp;
    
    private DefaultDiskStorage$EntryImpl(final String s, final File file) {
        Preconditions.checkNotNull(file);
        this.id = Preconditions.checkNotNull(s);
        this.resource = FileBinaryResource.createOrNull(file);
        this.size = -1L;
        this.timestamp = -1L;
    }
    
    @Override
    public String getId() {
        return this.id;
    }
    
    public FileBinaryResource getResource() {
        return this.resource;
    }
    
    @Override
    public long getSize() {
        if (this.size < 0L) {
            this.size = this.resource.size();
        }
        return this.size;
    }
    
    @Override
    public long getTimestamp() {
        if (this.timestamp < 0L) {
            this.timestamp = this.resource.getFile().lastModified();
        }
        return this.timestamp;
    }
}
