// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.Response;
import com.android.volley.NetworkResponse;
import java.io.UnsupportedEncodingException;
import com.android.volley.VolleyLog;
import com.android.volley.Response$ErrorListener;
import com.android.volley.Response$Listener;
import com.android.volley.Request;

public abstract class JsonRequest<T> extends Request<T>
{
    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE;
    private final Response$Listener<T> mListener;
    private final String mRequestBody;
    
    static {
        PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", "utf-8");
    }
    
    public JsonRequest(final int n, final String s, final String mRequestBody, final Response$Listener<T> mListener, final Response$ErrorListener response$ErrorListener) {
        super(n, s, response$ErrorListener);
        this.mListener = mListener;
        this.mRequestBody = mRequestBody;
    }
    
    public JsonRequest(final String s, final String s2, final Response$Listener<T> response$Listener, final Response$ErrorListener response$ErrorListener) {
        this(-1, s, s2, response$Listener, response$ErrorListener);
    }
    
    @Override
    protected void deliverResponse(final T t) {
        this.mListener.onResponse(t);
    }
    
    @Override
    public byte[] getBody() {
        try {
            if (this.mRequestBody == null) {
                return null;
            }
            return this.mRequestBody.getBytes("utf-8");
        }
        catch (UnsupportedEncodingException ex) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", this.mRequestBody, "utf-8");
            return null;
        }
    }
    
    @Override
    public String getBodyContentType() {
        return JsonRequest.PROTOCOL_CONTENT_TYPE;
    }
    
    @Override
    public byte[] getPostBody() {
        return this.getBody();
    }
    
    @Override
    public String getPostBodyContentType() {
        return this.getBodyContentType();
    }
    
    @Override
    protected abstract Response<T> parseNetworkResponse(final NetworkResponse p0);
}
