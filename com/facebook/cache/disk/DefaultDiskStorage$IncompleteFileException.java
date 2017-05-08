// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.cache.disk;

import java.io.IOException;

class DefaultDiskStorage$IncompleteFileException extends IOException
{
    public final long actual;
    public final long expected;
    
    public DefaultDiskStorage$IncompleteFileException(final long expected, final long actual) {
        super("File was not written completely. Expected: " + expected + ", found: " + actual);
        this.expected = expected;
        this.actual = actual;
    }
}
