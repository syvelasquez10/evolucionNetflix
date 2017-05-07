// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.File;
import java.io.FilenameFilter;

final class FileLruCache$BufferFile$2 implements FilenameFilter
{
    @Override
    public boolean accept(final File file, final String s) {
        return s.startsWith("buffer");
    }
}
