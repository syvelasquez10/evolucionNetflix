// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.File;

class DynamicDefaultDiskStorage$State
{
    public final DiskStorage delegate;
    public final File rootDirectory;
    
    DynamicDefaultDiskStorage$State(final File rootDirectory, final DiskStorage delegate) {
        this.delegate = delegate;
        this.rootDirectory = rootDirectory;
    }
}
