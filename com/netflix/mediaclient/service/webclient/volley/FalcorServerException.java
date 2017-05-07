// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.VolleyError;

public class FalcorServerException extends VolleyError
{
    private static String TAG;
    private static final long serialVersionUID = -8643186878472303994L;
    
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
    
    public static StatusCode getErrorCode(final String s, final ErrorLogging errorLogging) {
        final StatusCode server_ERROR = StatusCode.SERVER_ERROR;
        if (StringUtils.isEmpty(s)) {
            return server_ERROR;
        }
        if (Log.isLoggable(FalcorServerException.TAG, 3)) {
            Log.d(FalcorServerException.TAG, "errorMsg:" + s);
        }
        final String lowerCase = s.toLowerCase(Locale.US);
        StatusCode statusCode;
        if (FalcorParseUtils.isNotAuthorized(lowerCase)) {
            statusCode = StatusCode.USER_NOT_AUTHORIZED;
        }
        else if (FalcorParseUtils.isDeletedProfile(lowerCase)) {
            statusCode = StatusCode.DELETED_PROFILE;
        }
        else if (FalcorParseUtils.isNullPointerException(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("Endpoint NPE " + lowerCase);
            }
            statusCode = StatusCode.WRONG_PATH;
        }
        else if (FalcorParseUtils.isAlreadyInQueue(lowerCase)) {
            statusCode = StatusCode.ALREADY_IN_QUEUE;
        }
        else if (FalcorParseUtils.isNotInQueue(lowerCase)) {
            statusCode = StatusCode.NOT_IN_QUEUE;
        }
        else if (FalcorParseUtils.isMapCacheError(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("map cache miss");
            }
            statusCode = StatusCode.SERVER_ERROR_MAP_CACHE_MISS;
        }
        else {
            statusCode = server_ERROR;
            if (FalcorParseUtils.isMapError(lowerCase)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map error " + lowerCase);
                }
                statusCode = StatusCode.MAP_ERROR;
            }
        }
        return statusCode;
    }
}
