// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.UnsupportedEncodingException;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Request;

public class StringRequest extends Request<String>
{
    private final Response.Listener<String> mListener;
    
    public StringRequest(final int n, final String s, final Response.Listener<String> mListener, final Response.ErrorListener errorListener) {
        super(n, s, errorListener);
        this.mListener = mListener;
    }
    
    public StringRequest(final String s, final Response.Listener<String> listener, final Response.ErrorListener errorListener) {
        this(0, s, listener, errorListener);
    }
    
    @Override
    protected void deliverResponse(final String s) {
        this.mListener.onResponse(s);
    }
    
    @Override
    protected Response<String> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            final String s = new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers));
            return Response.success(s, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            final String s = new String(networkResponse.data);
            return Response.success(s, HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
    }
}
