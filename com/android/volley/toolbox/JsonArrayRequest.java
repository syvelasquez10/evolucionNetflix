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
import org.json.JSONArray;

public class JsonArrayRequest extends JsonRequest<JSONArray>
{
    public JsonArrayRequest(final String s, final Response.Listener<JSONArray> listener, final Response.ErrorListener errorListener) {
        super(0, s, null, listener, errorListener);
    }
    
    @Override
    protected Response<JSONArray> parseNetworkResponse(final NetworkResponse networkResponse) {
        try {
            return Response.success(new JSONArray(new String(networkResponse.data, HttpHeaderParser.parseCharset(networkResponse.headers))), HttpHeaderParser.parseCacheHeaders(networkResponse));
        }
        catch (UnsupportedEncodingException ex) {
            return Response.error(new ParseError(ex));
        }
        catch (JSONException ex2) {
            return Response.error(new ParseError((Throwable)ex2));
        }
    }
}
