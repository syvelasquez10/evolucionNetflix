// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.File;

final class FileLruCache$ModifiedFile implements Comparable<FileLruCache$ModifiedFile>
{
    private final File file;
    private final long modified;
    
    FileLruCache$ModifiedFile(final File file) {
        this.file = file;
        this.modified = file.lastModified();
    }
    
    @Override
    public int compareTo(final FileLruCache$ModifiedFile fileLruCache$ModifiedFile) {
        if (this.getModified() < fileLruCache$ModifiedFile.getModified()) {
            return -1;
        }
        if (this.getModified() > fileLruCache$ModifiedFile.getModified()) {
            return 1;
        }
        return this.getFile().compareTo(fileLruCache$ModifiedFile.getFile());
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof FileLruCache$ModifiedFile && this.compareTo((FileLruCache$ModifiedFile)o) == 0;
    }
    
    File getFile() {
        return this.file;
    }
    
    long getModified() {
        return this.modified;
    }
    
    @Override
    public int hashCode() {
        return (this.file.hashCode() + 1073) * 37 + (int)(this.modified % 2147483647L);
    }
}
