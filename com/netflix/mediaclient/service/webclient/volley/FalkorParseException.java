// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.StatusCode;
import com.android.volley.VolleyError;

public class FalkorParseException extends VolleyError
{
    private static String TAG;
    
    static {
        FalkorParseException.TAG = "FalkorParseException";
    }
    
    public FalkorParseException(final String s) {
        super(s);
    }
    
    public FalkorParseException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FalkorParseException(final Throwable t) {
        super(t);
    }
    
    public static StatusCode getErrorCode(final String s) {
        final StatusCode falkor_RESPONSE_PARSE_ERROR = StatusCode.FALKOR_RESPONSE_PARSE_ERROR;
        if (!StringUtils.isEmpty(s)) {
            if (Log.isLoggable()) {
                Log.d(FalkorParseException.TAG, "errorMsg:" + s);
            }
            if (isWrongState(s.toLowerCase(Locale.US))) {
                return StatusCode.BROWSE_AGENT_WRONG_STATE;
            }
        }
        return falkor_RESPONSE_PARSE_ERROR;
    }
    
    private static boolean isWrongState(final String s) {
        return s.contains("wrong state");
    }
}
