// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

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
            if (Log.isLoggable(FalkorServerException.TAG, 3)) {
                Log.d(FalkorServerException.TAG, "errorMsg:" + s);
            }
            if (FalkorParseUtils.isInvalidCounty(s)) {
                return StatusCode.INVALID_COUNRTY;
            }
            if (FalkorParseUtils.isNotAuthorized(s)) {
                return StatusCode.USER_NOT_AUTHORIZED;
            }
            if (FalkorParseUtils.isDeletedProfile(s)) {
                return StatusCode.DELETED_PROFILE;
            }
            if (FalkorParseUtils.isNullPointerException(s)) {
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
            if (FalkorParseUtils.isMapCacheError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map cache miss");
                }
                return StatusCode.SERVER_ERROR_MAP_CACHE_MISS;
            }
            if (FalkorParseUtils.isMapError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map error " + s);
                }
                return StatusCode.MAP_ERROR;
            }
        }
        return server_ERROR;
    }
}
