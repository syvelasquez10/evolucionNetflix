// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import java.util.Locale;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.StatusCode;
import com.android.volley.VolleyError;

public class FalcorParseException extends VolleyError
{
    private static String TAG;
    
    static {
        FalcorParseException.TAG = "FalcorParseException";
    }
    
    public FalcorParseException(final String s) {
        super(s);
    }
    
    public FalcorParseException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public FalcorParseException(final Throwable t) {
        super(t);
    }
    
    public static StatusCode getErrorCode(final String s) {
        StatusCode statusCode = StatusCode.FALCOR_RESPONSE_PARSE_ERROR;
        if (StringUtils.isEmpty(s)) {
            return statusCode;
        }
        Log.d(FalcorParseException.TAG, "errorMsg:" + s);
        if (FalcorParseUtils.isWrongState(s.toLowerCase(Locale.US))) {
            statusCode = StatusCode.BROWSE_AGENT_WRONG_STATE;
        }
        return statusCode;
    }
}
