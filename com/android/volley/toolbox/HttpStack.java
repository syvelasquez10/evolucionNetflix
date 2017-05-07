// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import com.android.volley.AuthFailureError;
import java.io.IOException;
import org.apache.http.HttpResponse;
import java.util.Map;
import com.android.volley.Request;

public interface HttpStack
{
    HttpResponse performRequest(final Request<?> p0, final Map<String, String> p1) throws IOException, AuthFailureError;
}
