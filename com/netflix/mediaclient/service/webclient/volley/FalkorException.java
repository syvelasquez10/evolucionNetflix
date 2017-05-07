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

public class FalkorException extends VolleyError
{
    private static String TAG;
    
    static {
        FalkorException.TAG = "FalkorException";
    }
    
    public FalkorException(final String s) {
        super(s);
    }
    
    public FalkorException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FalkorException(final Throwable t) {
        super(t);
    }
    
    public static StatusCode getErrorCode(final String s, final ErrorLogging errorLogging) {
        final StatusCode falkor_RESPONSE_PARSE_ERROR = StatusCode.FALKOR_RESPONSE_PARSE_ERROR;
        if (StringUtils.isEmpty(s)) {
            return falkor_RESPONSE_PARSE_ERROR;
        }
        if (Log.isLoggable()) {
            Log.d(FalkorException.TAG, "errorMsg:" + s);
        }
        final String lowerCase = s.toLowerCase(Locale.US);
        StatusCode statusCode;
        if (isWrongState(lowerCase)) {
            statusCode = StatusCode.BROWSE_AGENT_WRONG_STATE;
        }
        else if (isInvalidCounty(lowerCase)) {
            statusCode = StatusCode.INVALID_COUNRTY;
        }
        else if (isInsufficientContentError(lowerCase)) {
            statusCode = StatusCode.INSUFFICIENT_CONTENT;
        }
        else if (isNotAuthorized(lowerCase)) {
            statusCode = StatusCode.USER_NOT_AUTHORIZED;
        }
        else if (isDeletedProfile(lowerCase)) {
            statusCode = StatusCode.DELETED_PROFILE;
        }
        else if (isNullPointerException(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("Endpoint NPE " + lowerCase);
            }
            statusCode = StatusCode.WRONG_PATH;
        }
        else if (FalkorParseUtils.isAlreadyInQueue(lowerCase)) {
            statusCode = StatusCode.ALREADY_IN_QUEUE;
        }
        else if (FalkorParseUtils.isNotInQueue(lowerCase)) {
            statusCode = StatusCode.NOT_IN_QUEUE;
        }
        else if (isMapCacheError(lowerCase)) {
            if (errorLogging != null) {
                errorLogging.logHandledException("map cache miss");
            }
            statusCode = StatusCode.SERVER_ERROR_MAP_CACHE_MISS;
        }
        else {
            statusCode = falkor_RESPONSE_PARSE_ERROR;
            if (isMapError(lowerCase)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map error " + lowerCase);
                }
                statusCode = StatusCode.MAP_ERROR;
            }
        }
        Log.d(FalkorException.TAG, "statusCode :" + statusCode);
        return statusCode;
    }
    
    private static boolean isDeletedProfile(final String s) {
        return s.contains("deleted profile".toLowerCase());
    }
    
    private static boolean isInsufficientContentError(final String s) {
        return s.contains("failurereason=insufficientdata".toLowerCase());
    }
    
    private static boolean isInvalidCounty(final String s) {
        return s.contains("failurereason=invalidcountry".toLowerCase());
    }
    
    private static boolean isMapCacheError(final String s) {
        return s.contains("cache miss".toLowerCase()) || s.contains("mapgetcachedlistclient failed".toLowerCase()) || s.contains("cachemiss".toLowerCase());
    }
    
    private static boolean isMapError(final String s) {
        return s.contains("map error".toLowerCase());
    }
    
    private static boolean isNotAuthorized(final String s) {
        return s.contains("not authorized".toLowerCase()) || s.contains("unauthorized".toLowerCase());
    }
    
    private static boolean isNullPointerException(final String s) {
        return s.contains("nullpointerexception".toLowerCase());
    }
    
    private static boolean isWrongState(final String s) {
        return s.contains("wrong state".toLowerCase());
    }
}
