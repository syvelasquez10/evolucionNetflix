// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.File;

class DefaultDiskStorage$FileInfo
{
    public final String resourceId;
    public final DefaultDiskStorage$FileType type;
    
    private DefaultDiskStorage$FileInfo(final DefaultDiskStorage$FileType type, final String resourceId) {
        this.type = type;
        this.resourceId = resourceId;
    }
    
    public static DefaultDiskStorage$FileInfo fromFile(final File file) {
        final String name = file.getName();
        final int lastIndex = name.lastIndexOf(46);
        if (lastIndex <= 0) {
            return null;
        }
        final DefaultDiskStorage$FileType fromExtension = DefaultDiskStorage$FileType.fromExtension(name.substring(lastIndex));
        if (fromExtension == null) {
            return null;
        }
        String s2;
        final String s = s2 = name.substring(0, lastIndex);
        if (fromExtension.equals(DefaultDiskStorage$FileType.TEMP)) {
            final int lastIndex2 = s.lastIndexOf(46);
            if (lastIndex2 <= 0) {
                return null;
            }
            s2 = s.substring(0, lastIndex2);
        }
        return new DefaultDiskStorage$FileInfo(fromExtension, s2);
    }
    
    public File createTempFile(final File file) {
        return File.createTempFile(this.resourceId + ".", ".tmp", file);
    }
    
    public String toPath(final String s) {
        return s + File.separator + this.resourceId + this.type.extension;
    }
    
    @Override
    public String toString() {
        return this.type + "(" + this.resourceId + ")";
    }
}
