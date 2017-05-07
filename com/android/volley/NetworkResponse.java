// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

import java.util.Collections;
import java.util.Map;

public class NetworkResponse
{
    public final byte[] data;
    public final Map<String, String> headers;
    public final boolean notModified;
    public final int statusCode;
    
    public NetworkResponse(final int statusCode, final byte[] data, final Map<String, String> headers, final boolean notModified) {
        this.statusCode = statusCode;
        this.data = data;
        this.headers = headers;
        this.notModified = notModified;
    }
    
    public NetworkResponse(final byte[] array) {
        this(200, array, Collections.emptyMap(), false);
    }
    
    public NetworkResponse(final byte[] array, final Map<String, String> map) {
        this(200, array, map, false);
    }
}
