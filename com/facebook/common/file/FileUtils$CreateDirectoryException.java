// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.file;

import java.io.IOException;

public class FileUtils$CreateDirectoryException extends IOException
{
    public FileUtils$CreateDirectoryException(final String s) {
        super(s);
    }
    
    public FileUtils$CreateDirectoryException(final String s, final Throwable t) {
        super(s);
        this.initCause(t);
    }
}
