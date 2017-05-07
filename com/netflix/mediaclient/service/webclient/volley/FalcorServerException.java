// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

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
        if (!StringUtils.isEmpty(s)) {
            if (Log.isLoggable(FalcorServerException.TAG, 3)) {
                Log.d(FalcorServerException.TAG, "errorMsg:" + s);
            }
            if (FalcorParseUtils.isInvalidCounty(s)) {
                return StatusCode.INVALID_COUNRTY;
            }
            if (FalcorParseUtils.isNotAuthorized(s)) {
                return StatusCode.USER_NOT_AUTHORIZED;
            }
            if (FalcorParseUtils.isDeletedProfile(s)) {
                return StatusCode.DELETED_PROFILE;
            }
            if (FalcorParseUtils.isNullPointerException(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("Endpoint NPE " + s);
                }
                return StatusCode.WRONG_PATH;
            }
            if (FalcorParseUtils.isAlreadyInQueue(s)) {
                return StatusCode.ALREADY_IN_QUEUE;
            }
            if (FalcorParseUtils.isNotInQueue(s)) {
                return StatusCode.NOT_IN_QUEUE;
            }
            if (FalcorParseUtils.isMapCacheError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map cache miss");
                }
                return StatusCode.SERVER_ERROR_MAP_CACHE_MISS;
            }
            if (FalcorParseUtils.isMapError(s)) {
                if (errorLogging != null) {
                    errorLogging.logHandledException("map error " + s);
                }
                return StatusCode.MAP_ERROR;
            }
        }
        return server_ERROR;
    }
}
