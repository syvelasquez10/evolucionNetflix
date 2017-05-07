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
        if (message == null) {
            return wrong_PATH;
        }
        final String lowerCase = message.toLowerCase(Locale.US);
        Log.d(BaseWebClient.TAG, ".next call failed with error =" + lowerCase);
        StatusCode statusCode;
        if (lowerCase.contains("map error")) {
            statusCode = StatusCode.MAP_ERROR;
        }
        else if (lowerCase.contains("not authorized")) {
            statusCode = StatusCode.USER_NOT_AUTHORIZED;
        }
        else {
            statusCode = wrong_PATH;
            if (lowerCase.contains("path error")) {
                statusCode = StatusCode.WRONG_PATH;
            }
        }
        return statusCode;
    }
}
