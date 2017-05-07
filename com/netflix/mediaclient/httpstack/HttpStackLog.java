// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.httpstack;

import com.netflix.mediaclient.Log;
import org.apache.http.HttpResponse;

public class HttpStackLog
{
    private static final boolean ENABLE_LOGGING = false;
    private static String TAG;
    
    static {
        HttpStackLog.TAG = "HttpStack";
    }
    
    public static void dumpCookies(final HttpResponse httpResponse) {
        if (Log.isLoggable(HttpStackLog.TAG, 3)) {}
    }
}
