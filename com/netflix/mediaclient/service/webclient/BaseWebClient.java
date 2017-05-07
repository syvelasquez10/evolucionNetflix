// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.StatusCode;

public abstract class BaseWebClient
{
    private static String TAG;
    
    static {
        BaseWebClient.TAG = "BaseWebClient";
    }
    
    public static StatusCode getStatusCodeFromError(final Throwable t) {
        final StatusCode wrong_PATH = StatusCode.WRONG_PATH;
        Log.w(BaseWebClient.TAG, "Received Error", t);
        final String message = t.getMessage();
        if (message != null) {
            final String lowerCase = message.toLowerCase(Locale.US);
            Log.d(BaseWebClient.TAG, ".next call failed with error =" + lowerCase);
            if (lowerCase.contains("map error")) {
                return StatusCode.MAP_ERROR;
            }
            if (lowerCase.contains("not authorized")) {
                return StatusCode.USER_NOT_AUTHORIZED;
            }
            if (lowerCase.contains("path error")) {
                return StatusCode.WRONG_PATH;
            }
        }
        return wrong_PATH;
    }
}
