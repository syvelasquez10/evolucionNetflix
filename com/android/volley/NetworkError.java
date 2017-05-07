// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public class NetworkError extends VolleyError
{
    public NetworkError() {
    }
    
    public NetworkError(final NetworkResponse networkResponse) {
        super(networkResponse);
    }
    
    public NetworkError(final Throwable t) {
        super(t);
    }
}
