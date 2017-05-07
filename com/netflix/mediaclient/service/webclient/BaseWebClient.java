// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient;

import java.util.Locale;
import com.netflix.mediaclient.Log;

public abstract class BaseWebClient
{
    private static String TAG;
    
    static {
        BaseWebClient.TAG = "BaseWebClient";
    }
    
    public static int getFBConnectStatusCode(final String s) {
        if (!s.equals("202") && !s.equals("200")) {
            if (s.equals("400")) {
                return -50;
            }
            if (s.equals("401")) {
                return -51;
            }
            if (s.equals("403") || s.equals("405")) {
                return -52;
            }
            if (s.equals("406")) {
                return -53;
            }
            if (s.equals("500")) {
                return -54;
            }
            if (s.equals("503")) {
                return -55;
            }
        }
        return 0;
    }
    
    public static int getStatusCodeFromError(final Throwable t) {
        int n = -60;
        Log.w(BaseWebClient.TAG, "Received Error", t);
        final String message = t.getMessage();
        if (message == null) {
            return -60;
        }
        final String lowerCase = message.toLowerCase(Locale.US);
        Log.d(BaseWebClient.TAG, ".next call failed with error =" + lowerCase);
        if (lowerCase.contains("map error")) {
            n = -65;
        }
        else if (lowerCase.contains("not authorized")) {
            n = -61;
        }
        else if (lowerCase.contains("path error")) {
            n = -60;
        }
        return n;
    }
}
