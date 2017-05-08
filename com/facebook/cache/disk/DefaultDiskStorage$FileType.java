// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

enum DefaultDiskStorage$FileType
{
    CONTENT(".cnt"), 
    TEMP(".tmp");
    
    public final String extension;
    
    private DefaultDiskStorage$FileType(final String extension) {
        this.extension = extension;
    }
    
    public static DefaultDiskStorage$FileType fromExtension(final String s) {
        if (".cnt".equals(s)) {
            return DefaultDiskStorage$FileType.CONTENT;
        }
        if (".tmp".equals(s)) {
            return DefaultDiskStorage$FileType.TEMP;
        }
        return null;
    }
}
