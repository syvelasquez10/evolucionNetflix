// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import org.json.JSONException;
import java.io.UnsupportedEncodingException;
import com.android.volley.VolleyError;
import com.android.volley.ParseError;
import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import org.json.JSONObject;

public class JsonObjectRequest extends JsonRequest<JSONObject>
{
    public JsonObjectRequest(final int n, final String s, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        String string;
        if (jsonObject == null) {
            string = null;
        }
        else {
            string = jsonObject.toString();
        }
        super(n, s, string, listener, errorListener);
    }
    
    public JsonObjectRequest(final String s, final JSONObject jsonObject, final Response.Listener<JSONObject> listener, final Response.ErrorListener errorListener) {
        int n;
        if (jsonObject == null) {
            n = 0;
        }
        else {
            n = 1;
        }
        this(n, s, jsonObject, listener, errorListener);
    }
    
    @Override
    protected Response<JSONObject> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONObject(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }
        catch (JSONException ex2) {
            return Response.error(new ParseError((Throwable)ex2));
        }
    }
}
