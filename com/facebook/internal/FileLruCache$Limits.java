// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

public final class FileLruCache$Limits
{
    private int byteCount;
    private int fileCount;
    
    public FileLruCache$Limits() {
        this.fileCount = 1024;
        this.byteCount = 1048576;
    }
    
    int getByteCount() {
        return this.byteCount;
    }
    
    int getFileCount() {
        return this.fileCount;
    }
}
