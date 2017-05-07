// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.VolleyError;

public class FalcorServerException extends VolleyError
{
    private static String TAG;
    
    static {
        FalcorServerException.TAG = "FalcorServerException";
    }
    
    public FalcorServerException(final String s) {
        super(s);
    }
    
    public FalcorServerException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FalcorServerException(final Throwable t) {
        super(t);
    }
    
    public static int getErrorCode(String lowerCase, final ErrorLogging errorLogging) {
        int n = -62;
        if (StringUtils.isEmpty(lowerCase)) {
            return -62;
        }
        if (Log.isLoggable(FalcorServerException.TAG, 3)) {
            Log.d(FalcorServerException.TAG, "errorMsg:" + lowerCase);
        }
        lowerCase = lowerCase.toLowerCase(Locale.US);
        if (FalcorParseUtils.isNotAuthorized(lowerCase)) {
            n = -61;
        }
        else if (FalcorParseUtils.isNullPointerException(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("Endpoint NPE " + lowerCase);
            }
            n = -60;
        }
        else if (FalcorParseUtils.isMapCacheError(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("map cache miss");
            }
            n = -64;
        }
        else if (FalcorParseUtils.isMapError(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("map error " + lowerCase);
            }
            n = -65;
        }
        return n;
    }
}
