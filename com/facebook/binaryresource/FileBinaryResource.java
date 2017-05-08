// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.binaryresource;

import java.io.FileInputStream;
import java.io.InputStream;
import com.facebook.common.internal.Preconditions;
import java.io.File;

public class FileBinaryResource implements BinaryResource
{
    private final File mFile;
    
    private FileBinaryResource(final File file) {
        this.mFile = Preconditions.checkNotNull(file);
    }
    
    public static FileBinaryResource createOrNull(final File file) {
        if (file != null) {
            return new FileBinaryResource(file);
        }
        return null;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o instanceof FileBinaryResource && this.mFile.equals(((FileBinaryResource)o).mFile);
    }
    
    public File getFile() {
        return this.mFile;
    }
    
    @Override
    public int hashCode() {
        return this.mFile.hashCode();
    }
    
    @Override
    public InputStream openStream() {
        return new FileInputStream(this.mFile);
    }
    
    @Override
    public long size() {
        return this.mFile.length();
    }
}
