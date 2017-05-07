// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley;

public class VolleyError extends Exception
{
    public final NetworkResponse networkResponse;
    
    public VolleyError() {
        this.networkResponse = null;
    }
    
    public VolleyError(final NetworkResponse networkResponse) {
        this.networkResponse = networkResponse;
    }
    
    public VolleyError(final String s) {
        super(s);
        this.networkResponse = null;
    }
    
    public VolleyError(final String s, final Throwable t) {
        super(s, t);
        this.networkResponse = null;
    }
    
    public VolleyError(final Throwable t) {
        super(t);
        this.networkResponse = null;
    }
}
