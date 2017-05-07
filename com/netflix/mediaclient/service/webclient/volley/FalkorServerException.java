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

public class FalkorServerException extends VolleyError
{
    private static String TAG;
    private static final long serialVersionUID = -8643186878472303994L;
    
    static {
        FalkorServerException.TAG = "FalkorServerException";
    }
    
    public FalkorServerException(final String s) {
        super(s);
    }
    
    public FalkorServerException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FalkorServerException(final Throwable t) {
        super(t);
    }
    
    public static StatusCode getErrorCode(final String s, final ErrorLogging errorLogging) {
        final StatusCode server_ERROR = StatusCode.SERVER_ERROR;
        if (!StringUtils.isEmpty(s)) {
            if (Log.isLoggable()) {
                Log.d(FalkorServerException.TAG, "errorMsg:" + s);
            }
            if (isInvalidCounty(s)) {
                return StatusCode.INVALID_COUNRTY;
            }
            if (isNotAuthorized(s)) {
                return StatusCode.USER_NOT_AUTHORIZED;
            }
            if (isDeletedProfile(s)) {
                return StatusCode.DELETED_PROFILE;
            }
            if (isNullPointerException(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("Endpoint NPE " + s);
                }
                return StatusCode.WRONG_PATH;
            }
            if (FalkorParseUtils.isAlreadyInQueue(s)) {
                return StatusCode.ALREADY_IN_QUEUE;
            }
            if (FalkorParseUtils.isNotInQueue(s)) {
                return StatusCode.NOT_IN_QUEUE;
            }
            if (isMapCacheError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map cache miss");
                }
                return StatusCode.SERVER_ERROR_MAP_CACHE_MISS;
            }
            if (isMapError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map error " + s);
                }
                return StatusCode.MAP_ERROR;
            }
        }
        return server_ERROR;
    }
    
    private static boolean isDeletedProfile(final String s) {
        return s.toLowerCase(Locale.US).contains("deleted profile");
    }
    
    private static boolean isInvalidCounty(final String s) {
        return s.toLowerCase(Locale.US).contains("failurereason=invalidcountry");
    }
    
    private static boolean isMapCacheError(final String s) {
        return s.contains("cache miss") || s.contains("mapgetcachedlistclient failed") || s.contains("cachemiss");
    }
    
    private static boolean isMapError(final String s) {
        return s.toLowerCase(Locale.US).contains("map error");
    }
    
    private static boolean isNotAuthorized(final String s) {
        return s.toLowerCase(Locale.US).contains("not authorized") || s.toLowerCase(Locale.US).contains("unauthorized");
    }
    
    private static boolean isNullPointerException(final String s) {
        return s.toLowerCase(Locale.US).contains("nullpointerexception");
    }
}
