// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.file;

import java.io.IOException;

public class FileUtils$RenameException extends IOException
{
    public FileUtils$RenameException(final String s, final Throwable t) {
        super(s);
        this.initCause(t);
    }
}
