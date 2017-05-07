// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.IOException;
import com.netflix.mediaclient.Log;
import java.io.Closeable;

public class IoUtil
{
    private static final String TAG = "IoUtil";
    
    public static void safeClose(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {
            Log.handleException("IoUtil", ex);
        }
    }
}
