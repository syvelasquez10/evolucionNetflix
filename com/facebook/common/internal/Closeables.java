// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.io.InputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.io.Closeable;
import java.util.logging.Logger;

public final class Closeables
{
    static final Logger logger;
    
    static {
        logger = Logger.getLogger(Closeables.class.getName());
    }
    
    public static void close(final Closeable closeable, final boolean b) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {
            if (b) {
                Closeables.logger.log(Level.WARNING, "IOException thrown while closing Closeable.", ex);
                return;
            }
            throw ex;
        }
    }
    
    public static void closeQuietly(final InputStream inputStream) {
        try {
            close(inputStream, true);
        }
        catch (IOException ex) {
            throw new AssertionError((Object)ex);
        }
    }
}
