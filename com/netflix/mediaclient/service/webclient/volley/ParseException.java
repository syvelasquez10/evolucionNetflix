// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.VolleyError;

public class ParseException extends VolleyError
{
    public ParseException() {
    }
    
    public ParseException(final NetworkResponse networkResponse) {
        super(networkResponse);
    }
    
    public ParseException(final String s) {
        super(s);
    }
    
    public ParseException(final String s, final Throwable t) {
        super(s, t);
    }
    
    public ParseException(final Throwable t) {
        super(t);
    }
}
